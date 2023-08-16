package project.personal.social.network.service;

import java.util.List;
import java.util.UUID;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;

public interface RoomService {

	List<RoomRes> getRooms();

	RoomDetailRes getRoomDetailById(UUID id) throws EntityNotFoundException;

	RoomRes createRoom(RoomReq req);

	void deleteRoom(UUID id) throws EntityNotFoundException;

	void updateRoom(UUID id, RoomReq req) throws EntityNotFoundException;

}
