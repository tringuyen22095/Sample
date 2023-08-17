package project.personal.social.network.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;

public interface FileService {

	String storeFile(MultipartFile file) throws FileStorageException;

	Resource loadFileAsResource(String fileName) throws FileStorageNotFoundException;

}
