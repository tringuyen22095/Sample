package project.personal.social.network.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.stream.LongStream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import project.personal.shared.client.resource.DocumentFeignClient;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.shared.common.model.req.DocumentReq;
import project.personal.social.network.model.FileCombineEvent;
import project.personal.social.network.service.FileService;

@Service
@Profile("!dev")
public class FileServiceImpl implements FileService {

	private static final Logger _log = LoggerFactory.getLogger(FileServiceImpl.class);

	private Map<String, List<byte[]>> concurrentMap;

	private ReentrantReadWriteLock lock;

	private ReadLock readLock;

	private WriteLock writeLock;

	@Autowired
	private DocumentFeignClient documentFeignClient;

	@PostConstruct
	public void init() {
		this.concurrentMap = new Hashtable<String, List<byte[]>>();
		this.lock = new ReentrantReadWriteLock();
		this.readLock = this.lock.readLock();
		this.writeLock = this.lock.writeLock();
	}

	@Override
	public String storeFile(final MultipartFile file, String... customName) throws FileStorageException {
		_log.info("Database storeFile executed!! => not supported!!");
		return null;
	}

	@Override
	public Resource loadFileAsResource(final String fileName) throws FileStorageNotFoundException {
		_log.info("Database loadFileAsResource executed!! => not supported!!");
		return null;
	}

	@Async
	@EventListener(condition = "#event.fileName != null && #event.fileName.length != 0 && #event.chunkIndex >= 0 && #event.fileSize > 0 && #event.docId != null")
	public void fileCombineEventListener(final FileCombineEvent event)
			throws InterruptedException, FileStorageException, FileStorageNotFoundException, IOException {
		final MultipartFile source = (MultipartFile) event.getSource();
		final UUID docId = event.getDocId();
		final String key = event.getFileName() + docId.toString();
		final Long value = event.getChunkIndex();
		final long totalChunksCount = event.getTotalChunks();
		_log.info("Event listening compine file with file name {}, index [{}]", key, value);
		_log.info("totalChunks {}", totalChunksCount);

		try {
			this.writeLock.lock();
			this.concurrentMap.compute(key, (k, v) -> {
				try {
					if (v == null) {
						List<byte[]> newLst = new ArrayList<byte[]>();
						LongStream.range(0, totalChunksCount).forEach(i -> newLst.add(null));
						newLst.set(value.intValue(), source.getBytes());
						return newLst;
					}
					v.set(value.intValue(), source.getBytes());
					return v;
				} catch (IOException e) {
					return null;
				}
			});
		} finally {
			this.writeLock.unlock();
		}

		try {
			this.readLock.lock();
			if (this.concurrentMap.containsKey(key) &&
					CollectionUtils.isNotEmpty(this.concurrentMap.get(key)) &&
					!IterableUtils.matchesAny(this.concurrentMap.get(key), (ele) -> ele == null) &&
					this.concurrentMap.get(key).size() == totalChunksCount) {
				List<byte[]> arr = this.concurrentMap.get(key);
				this.concurrentMap.remove(key);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				for (byte[] b : arr) byteArrayOutputStream.write(b);

				DocumentReq docReq = DocumentReq.builder()
						.bData(byteArrayOutputStream.toByteArray())
						.build();
				this.documentFeignClient.updateDocument(docId, docReq);
			}
		} catch (EntityNotFoundException e) {
			_log.info("Feign client executed error for {}", key, e);
			this.concurrentMap.remove(key);
		} finally {
			this.readLock.unlock();
		}
	}

	@SuppressWarnings("unused")
	@Deprecated
	private long countTotalChunksIndex(final long totalChunks) {
		return LongStream.rangeClosed(1l, totalChunks).sum();
	}

}
