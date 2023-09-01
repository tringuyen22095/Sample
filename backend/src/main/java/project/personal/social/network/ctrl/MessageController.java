package project.personal.social.network.ctrl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.social.network.model.FileCombineEvent;
import project.personal.social.network.service.FileService;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

	private static final Logger _log = LoggerFactory.getLogger(MessageController.class);
	
	private final FileService fileService;
    
	private final ApplicationEventPublisher applicationEventPublisher;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void uploadFiles(@RequestParam("files") MultipartFile[] files) throws FileStorageException {
		for (MultipartFile file : files) {
			_log.info("Upload file {}", file.getOriginalFilename());
			this.fileService.storeFile(file);
		}
	}
	
	@PostMapping(value = "/async")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void uploadAsyncFile(@RequestParam("chunk") final MultipartFile chunk,
			@RequestParam("chunkIndex") final Long chunkIndex,
			@RequestParam("totalChunks") final Long totalChunks,
			@RequestHeader(name = "File-Name") final String fileName) {
		final long fileSize = chunk.getSize();
		_log.info("Upload chunkIndex {} for file name {} with size {} bytes", chunkIndex, fileName, fileSize);
		this.applicationEventPublisher.publishEvent(new FileCombineEvent(chunk, fileName, chunkIndex, fileSize, totalChunks));
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request) throws FileStorageNotFoundException {
		// Load file as Resource
//        Resource resource = this.fileService.loadFileAsResource(fileName);

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(new ByteArrayResource(new byte[] {}));
	}

}
