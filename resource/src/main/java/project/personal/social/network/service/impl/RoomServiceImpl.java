package project.personal.social.network.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;
import project.personal.social.network.entity.RoomEntity;
import project.personal.social.network.repo.RoomRepository;
import project.personal.social.network.resource.DataMapper;
import project.personal.social.network.service.RoomService;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;

	private final DataMapper mapper = DataMapper.INSTANCE;

	@Override
	public List<RoomRes> getRooms() {
		return this.roomRepository.findAll().stream().map(mapper::fromEntityToResponse).collect(Collectors.toList());
	}

	@Override
	public RoomDetailRes getRoomDetailById(UUID id) throws EntityNotFoundException {
		RoomEntity entity = this.findById(id);
		return this.mapper.fromEntityToResponseDetail(entity);
	}

	@Override
	public RoomRes createRoom(RoomReq req) {
		RoomEntity entity = this.roomRepository.saveAndFlush(this.mapper.fromRequestToEntity(req));
		return this.mapper.fromEntityToResponse(entity);
	}

	@Override
	public void deleteRoom(UUID id) throws EntityNotFoundException {
		RoomEntity entity = this.findById(id);
		entity.setDeleted(true);
		this.roomRepository.saveAndFlush(entity);
	}

	@Override
	public void updateRoom(UUID id, RoomReq req) throws EntityNotFoundException {
		RoomEntity entity = this.findById(id);
		this.mapper.fromRequestToEntity(entity, req);
		this.roomRepository.saveAndFlush(entity);
	}

	private RoomEntity findById(UUID id) throws EntityNotFoundException {
		Optional<RoomEntity> entityOpt = this.roomRepository.findById(id);
		if (!entityOpt.isPresent())
			throw new EntityNotFoundException(String.format("Room %s is not found", id));

		return entityOpt.get();
	}

}
