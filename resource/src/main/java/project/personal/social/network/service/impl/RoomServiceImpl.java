package project.personal.social.network.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.personal.social.network.entity.RoomEntity;
import project.personal.social.network.repo.RoomRepository;
import project.personal.social.network.service.RoomService;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;

	@Override
	public List<RoomEntity> getRooms() {
		return this.roomRepository.findAll();
	}

	@Override
	public RoomEntity createRoom(RoomEntity entity) {
		return this.roomRepository.save(entity);
	}

	@Override
	public void deleteRoom(UUID id) {
		this.roomRepository.deleteById(id);
	}

	@Override
	public void updateRoom(RoomEntity entity) {
		this.roomRepository.save(entity);
	}
}
