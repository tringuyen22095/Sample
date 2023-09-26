package project.personal.social.network.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;

public interface RoomService {

	ResponseEntity<List<RoomRes>> getRooms();

	ResponseEntity<RoomDetailRes> getRoomDetail(UUID id) throws EntityNotFoundException;

	ResponseEntity<RoomRes> createRoom(RoomReq req);

	ResponseEntity<Void> deleteRoom(UUID id) throws EntityNotFoundException;

	ResponseEntity<Void> updateRoom(UUID id, RoomReq req) throws EntityNotFoundException;

}
