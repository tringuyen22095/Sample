package project.personal.social.network.ctrl;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.social.network.service.MessageService;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
@Validated
public class MessageController {

	private final MessageService messageService;

	@GetMapping("/{roomId}")
	public ResponseEntity<Page<MessageRes>> getMessage(@PathVariable("roomId") UUID roomId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(name = "properties", defaultValue = "updatedOn, createdOn") String... properties)
			throws EntityNotFoundException {
		Pageable pageable = PageRequest.of(page, size, direction, properties);
		return ResponseEntity.ok(this.messageService.getMessages(roomId, pageable));
	}

	@PostMapping
	public ResponseEntity<MessageRes> createMessage(@Valid @RequestBody MessageReq req) throws EntityNotFoundException {
		return ResponseEntity.ok(this.messageService.createMessage(req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable("id") UUID id) throws EntityNotFoundException {
		this.messageService.deleteMessage(id);
		return ResponseEntity.accepted().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRoom(@PathVariable("id") UUID id, @Valid @RequestBody MessageReq req)
			throws EntityNotFoundException {
		this.messageService.updateMessage(id, req);
		return ResponseEntity.accepted().build();
	}

}
