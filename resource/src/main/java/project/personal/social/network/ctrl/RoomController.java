package project.personal.social.network.ctrl;

import java.util.List;
import java.util.UUID;

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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;
import project.personal.social.network.service.RoomService;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
@Validated
public class RoomController {

	private final RoomService roomService;

	@GetMapping
	public ResponseEntity<List<RoomRes>> getRooms() {
		return ResponseEntity.ok(this.roomService.getRooms());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoomDetailRes> getRoomDetail(@PathVariable("id") UUID id) throws EntityNotFoundException {
		return ResponseEntity.ok(this.roomService.getRoomDetailById(id));
	}

	@PostMapping
	public ResponseEntity<RoomRes> createRoom(@Valid @RequestBody RoomReq req) {
		return ResponseEntity.ok(this.roomService.createRoom(req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable("id") UUID id) throws EntityNotFoundException {
		this.roomService.deleteRoom(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRoom(@PathVariable("id") UUID id, @Valid @RequestBody RoomReq req)
			throws EntityNotFoundException {
		this.roomService.updateRoom(id, req);
		return ResponseEntity.noContent().build();
	}

}
