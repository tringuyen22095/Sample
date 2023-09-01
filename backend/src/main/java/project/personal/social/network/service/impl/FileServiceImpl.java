package project.personal.social.network.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.LongStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.social.network.model.FileCombineEvent;
import project.personal.social.network.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger _log = LoggerFactory.getLogger(FileServiceImpl.class);

	private Path fileStorageLocation;

	private Map<String, Long> concurrentMap;

	@PostConstruct
	public void init() {
		this.fileStorageLocation = Paths.get("./").toAbsolutePath().normalize();
		this.concurrentMap = new Hashtable<String, Long>();
	}

	@Override
	public String storeFile(final MultipartFile file, String... customName) throws FileStorageException {
		// Normalize file name
		String fileName = customName == null || customName.length == 0
				? StringUtils.cleanPath(file.getOriginalFilename())
				: customName[0];

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public Resource loadFileAsResource(final String fileName) throws FileStorageNotFoundException {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileStorageNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileStorageNotFoundException("File not found " + fileName, ex);
		}
	}

	@Async
	@EventListener(condition = "#event.fileName != null && #event.fileName.length != 0 && #event.chunkIndex >= 0 && #event.fileSize > 0")
	public void fileCombineEventListener(final FileCombineEvent event)
			throws InterruptedException, FileStorageException, FileStorageNotFoundException, IOException {
		final MultipartFile source = (MultipartFile) event.getSource();
		final String key = event.getFileName();
		final Long value = event.getChunkIndex();
		final String fileNameAsynchronize = String.format("(%d)%s", value, key);
		final long totalChunksCount = this.countTotalChunksIndex(event.getTotalChunks());
		_log.info("Event listening compine file as temporary with file name {}, index [{}]", key, value);

		this.concurrentMap.compute(key, (k, v) -> (v == null) ? value : (v + value));
		if (this.concurrentMap.get(key) != totalChunksCount) {
			this.storeFile(source, fileNameAsynchronize);
		} else {
			this.concurrentMap.remove(key);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] arr;
			for (int i = 1; i <= event.getTotalChunks(); i++) {
				if (i != value) {
					final String fileName = String.format("(%d)%s", i, key);
					Resource resource = this.loadFileAsResource(fileName);
					arr = IOUtils.toByteArray(resource.getInputStream());
//					Files.deleteIfExists(this.fileStorageLocation.resolve(fileName));
				} else {
					arr = source.getBytes();
				}
				byteArrayOutputStream.write(arr);
			}
			Path targetLocation = this.fileStorageLocation.resolve(key);
			try (OutputStream outputStream = new FileOutputStream(targetLocation.toFile())) {
				byteArrayOutputStream.writeTo(outputStream);
			}
		}
	}

	private long countTotalChunksIndex(final long totalChunks) {
		return LongStream.rangeClosed(1l, totalChunks).sum();
	}
	

}
