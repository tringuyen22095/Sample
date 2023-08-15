package project.personal.social.network.service;

import java.util.List;
import java.util.UUID;

import project.personal.social.network.entity.RoomEntity;

public interface RoomService {

	List<RoomEntity> getRooms();

	RoomEntity createRoom(RoomEntity entity);

	void deleteRoom(UUID id);

	void updateRoom(RoomEntity entity);

}
