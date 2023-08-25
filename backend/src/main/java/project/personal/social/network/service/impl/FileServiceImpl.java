package project.personal.social.network.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.social.network.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	private Path fileStorageLocation;

	@PostConstruct
	public void init() {
		this.fileStorageLocation = Paths.get("./").toAbsolutePath().normalize();
	}

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
}
