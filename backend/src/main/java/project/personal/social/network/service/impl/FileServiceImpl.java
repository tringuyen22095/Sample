package project.personal.social.network.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.social.network.service.FileService;

@Service
@Profile("prod")
public class FileServiceImpl implements FileService {

	private static final Logger _log = LoggerFactory.getLogger(FileServiceImpl.class);

	@Override
	public String storeFile(final MultipartFile file, String... customName) throws FileStorageException {
		_log.info("Prod storeFile executed!! => not supported!!");
		return null;
	}

	@Override
	public Resource loadFileAsResource(final String fileName) throws FileStorageNotFoundException {
		_log.info("Prod loadFileAsResource executed!! => not supported!!");
		return null;
	}

}
