package project.personal.social.network.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void init() {
		this.fileStorageLocation = Paths.get("./").toAbsolutePath().normalize();
	}

	@Override
	public String storeFile(MultipartFile file, String... customName) throws FileStorageException {
		// Normalize file name
		String fileName = customName == null || customName.length == 0 ? StringUtils.cleanPath(file.getOriginalFilename()) : customName[0];

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
	public Resource loadFileAsResource(String fileName) throws FileStorageNotFoundException {
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
	public void fileCombineEventListener(FileCombineEvent event) throws InterruptedException {
		_log.info("Event listening combine file for file name {}, index [{}]", event.getFileName(),
				event.getChunkIndex());

	}

}
