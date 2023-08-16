package project.personal.social.network.ctrl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<MessageRes>> getMessage(@PathVariable("roomId") UUID roomId) {
		return ResponseEntity.ok(this.messageService.getMessages(roomId));
	}

	@PostMapping
	public ResponseEntity<MessageRes> createMessage(@Valid @RequestBody MessageReq req) {
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
