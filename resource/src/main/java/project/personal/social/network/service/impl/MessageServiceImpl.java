package project.personal.social.network.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.social.network.entity.MessageEntity;
import project.personal.social.network.repo.MessageRepository;
import project.personal.social.network.resource.DataMapper;
import project.personal.social.network.service.MessageService;
import project.personal.social.network.service.RoomService;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

	private static final Logger _log = LoggerFactory.getLogger(MessageServiceImpl.class);

	private final MessageRepository messageRepository;

	private final RoomService roomService;

	private final DataMapper mapper = DataMapper.INSTANCE;

	@Override
	public Page<MessageRes> getMessages(UUID roomId, Pageable pageable) throws EntityNotFoundException {
		_log.info("Check room {} is available", roomId);
		this.roomService.getRoom(roomId);

		_log.info("Get message as paging");
		Page<MessageEntity> paging = this.messageRepository.findAllByRoomId(roomId, pageable);
		List<MessageRes> lstMsg = paging.getContent().stream().map(mapper::fromEntityToResponse)
				.collect(Collectors.toList());
		return new PageImpl<MessageRes>(lstMsg, pageable, paging.getTotalElements());
	}

	@Override
	public MessageRes createMessage(MessageReq req) throws EntityNotFoundException {
		_log.info("Check room {} is available", req.getRoomId());
		this.roomService.getRoom(req.getRoomId());

		MessageEntity entity = this.mapper.fromRequestToEntity(req);
		_log.info("Save message with body: {}", entity);
		entity = this.messageRepository.saveAndFlush(entity);
		return this.mapper.fromEntityToResponse(entity);
	}

	@Override
	public void deleteMessage(UUID id) throws EntityNotFoundException {
		MessageEntity entity = this.findById(id);

		_log.info("Delete message {}", id);
		entity.setDeleted(true);
		this.messageRepository.saveAndFlush(entity);
	}

	@Override
	public void updateMessage(UUID id, MessageReq req) throws EntityNotFoundException {
		_log.info("Check room {} is available", req.getRoomId());
		this.roomService.getRoom(req.getRoomId());

		MessageEntity entity = this.findById(id);

		this.mapper.fromRequestToExistEntity(entity, req);
		_log.info("Update message with body: {}", entity);
		this.messageRepository.saveAndFlush(entity);
	}

	private MessageEntity findById(UUID id) throws EntityNotFoundException {
		_log.info("Find message by {}", id);
		Optional<MessageEntity> entityOpt = this.messageRepository.findById(id);
		if (!entityOpt.isPresent())
			throw new EntityNotFoundException(String.format("Message %s is not found", id));

		return entityOpt.get();
	}

}
