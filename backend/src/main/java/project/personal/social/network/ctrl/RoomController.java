package project.personal.social.network.ctrl;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;
import project.personal.social.network.service.RoomService;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
@Validated
public class RoomController {

	private static final Logger _log = LoggerFactory.getLogger(RoomController.class);

	private final RoomService roomService;

	@GetMapping
	public ResponseEntity<List<RoomRes>> getRooms() {
		_log.info("Api getRooms was called");
		return this.roomService.getRooms();
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoomDetailRes> getRoomDetail(@PathVariable("id") UUID id) throws EntityNotFoundException {
		_log.info("Api getRoomDetail was called");
		return this.roomService.getRoomDetail(id);
	}

	@PostMapping
	public ResponseEntity<RoomRes> createRoom(@Valid @RequestBody RoomReq req) {
		_log.info("Api createRoom was called");
		return this.roomService.createRoom(req);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable("id") UUID id) throws EntityNotFoundException {
		_log.info("Api deleteRoom was called");
		return this.roomService.deleteRoom(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateRoom(@PathVariable("id") UUID id, @Valid @RequestBody RoomReq req)
			throws EntityNotFoundException {
		_log.info("Api updateRoom was called");
		return this.roomService.updateRoom(id, req);
	}

}
