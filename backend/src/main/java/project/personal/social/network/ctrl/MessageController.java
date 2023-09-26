package project.personal.social.network.ctrl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.exception.FileStorageException;
import project.personal.shared.common.exception.FileStorageNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.social.network.model.FileCombineEvent;
import project.personal.social.network.service.FileService;
import project.personal.social.network.service.MessageService;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
@Validated
public class MessageController {

	private static final Logger _log = LoggerFactory.getLogger(MessageController.class);

	private final MessageService messageService;

	private final FileService fileService;

	private final ApplicationEventPublisher applicationEventPublisher;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, value = "/non-async")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void uploadFiles(@RequestParam("files") MultipartFile[] files) throws FileStorageException {
		for (MultipartFile file : files) {
			_log.info("Upload file {}", file.getOriginalFilename());
			this.fileService.storeFile(file);
		}
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileName") String fileName,
			HttpServletRequest request) throws FileStorageNotFoundException {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(new ByteArrayResource(new byte[] {}));
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<Page<MessageRes>> getMessage(@PathVariable("roomId") UUID roomId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(name = "properties", defaultValue = "updatedOn, createdOn") String... properties)
			throws EntityNotFoundException {
		_log.info("Api getMessage was called");
		return this.messageService.getMessage(roomId, page, size, direction, properties);
	}

	@PostMapping
	public ResponseEntity<MessageRes> createMessage(@Valid @RequestBody MessageReq req) throws EntityNotFoundException, InterruptedException {
		_log.info("Api createMessage was called");
		return this.messageService.createMessage(req);
	}

	@PostMapping(value = "/async")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void uploadAsyncFile(@RequestParam("chunk") final MultipartFile chunk,
			@RequestParam("chunkIndex") final Long chunkIndex,
			@RequestParam("totalChunks") final Long totalChunks,
			@RequestParam("roomId") final UUID roomId,
			@RequestHeader(name = "File-Name") final String fileName,
			@RequestHeader(name = "File-Type") final String fileType) {
		final long fileSize = chunk.getSize();
		_log.info("Upload chunkIndex {} for file name {} with size {} bytes", chunkIndex, fileName, fileSize);
		this.applicationEventPublisher
				.publishEvent(new FileCombineEvent(chunk, fileName, chunkIndex, fileSize, totalChunks, fileType, roomId));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") UUID id) throws EntityNotFoundException {
		_log.info("Api deleteMessage was called");
		return this.messageService.deleteMessage(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRoom(@PathVariable("id") UUID id, @Valid @RequestBody MessageReq req)
			throws EntityNotFoundException {
		_log.info("Api updateRoom was called");
		return this.messageService.updateRoom(id, req);
	}

}
