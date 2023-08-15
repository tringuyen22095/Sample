package project.personal.social.network.ctrl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import project.personal.social.network.entity.RoomEntity;
import project.personal.social.network.service.RoomService;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@GetMapping
	public ResponseEntity<List<RoomEntity>> getRooms() {
		return ResponseEntity.ok(this.roomService.getRooms());
	}

	@PostMapping
	public ResponseEntity<RoomEntity> createRoom(@RequestBody RoomEntity req) {
		return ResponseEntity.ok(this.roomService.createRoom(req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable("id") UUID id) {
		this.roomService.deleteRoom(id);
		return ResponseEntity.accepted().build();
	}

	@PutMapping
	public ResponseEntity<Void> updateRoom(@RequestBody RoomEntity req) {
		this.roomService.updateRoom(req);
		return ResponseEntity.accepted().build();
	}

}
