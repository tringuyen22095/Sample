package project.personal.social.network.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.personal.shared.client.resource.RoomFeignClient;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;
import project.personal.social.network.service.RoomService;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomFeignClient roomFeignClient;

	@Override
	public ResponseEntity<List<RoomRes>> getRooms() {
		return this.roomFeignClient.getRooms();
	}

	@Override
	public ResponseEntity<RoomDetailRes> getRoomDetail(UUID id) throws EntityNotFoundException {
		return this.roomFeignClient.getRoomDetail(id);
	}

	@Override
	public ResponseEntity<RoomRes> createRoom(RoomReq req) {
		return this.roomFeignClient.createRoom(req);
	}

	@Override
	public ResponseEntity<Void> deleteRoom(UUID id) throws EntityNotFoundException {
		return this.roomFeignClient.deleteRoom(id);
	}

	@Override
	public ResponseEntity<Void> updateRoom(UUID id, RoomReq req) throws EntityNotFoundException {
		return this.roomFeignClient.updateRoom(id, req);
	}

}
