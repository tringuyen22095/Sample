package project.personal.social.network.ctrl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.DocumentReq;
import project.personal.shared.common.model.res.DocumentRes;
import project.personal.social.network.service.DocumentService;

@RestController
@RequestMapping("/document")
@AllArgsConstructor
@Validated
public class DocumentController {

	private final DocumentService documentService;

	@GetMapping("/{roomId}")
	public ResponseEntity<Page<DocumentRes>> documentPagingByRoomId(@PathVariable("roomId") UUID roomId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(name = "properties", defaultValue = "updatedOn, createdOn") String... properties)
			throws EntityNotFoundException {
		Pageable pageable = PageRequest.of(page, size, direction, properties);
		return ResponseEntity.ok(this.documentService.pagingByRoomId(roomId, pageable));
	}

	@PutMapping("/{docId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateDocument(@PathVariable("docId") UUID id, @RequestBody DocumentReq req)
			throws EntityNotFoundException {
		this.documentService.updateDocument(id, req);
	}

}
