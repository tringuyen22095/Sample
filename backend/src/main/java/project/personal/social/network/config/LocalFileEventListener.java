package project.personal.social.network.config;

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
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import project.personal.shared.client.resource.DocumentFeignClient;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.DocumentReq;
import project.personal.social.network.model.FileCombineEvent;

@Component
@Profile("!prod")
public class LocalFileEventListener implements ApplicationListener<FileCombineEvent> {

	private static final Logger _log = LoggerFactory.getLogger(LocalFileEventListener.class);

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
	public void onApplicationEvent(FileCombineEvent event) {
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
			if (this.concurrentMap.containsKey(key) && CollectionUtils.isNotEmpty(this.concurrentMap.get(key))
					&& !IterableUtils.matchesAny(this.concurrentMap.get(key), (ele) -> ele == null)
					&& this.concurrentMap.get(key).size() == totalChunksCount) {
				List<byte[]> arr = this.concurrentMap.get(key);
				this.concurrentMap.remove(key);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				for (byte[] b : arr)
					byteArrayOutputStream.write(b);

				DocumentReq docReq = DocumentReq.builder().bData(byteArrayOutputStream.toByteArray()).build();
				this.documentFeignClient.updateDocument(docId, docReq);
			}
		} catch (EntityNotFoundException | IOException e) {
			_log.info("Feign client executed error for {}", key, e);
			this.concurrentMap.remove(key);
		} finally {
			this.readLock.unlock();
		}
	}

}
