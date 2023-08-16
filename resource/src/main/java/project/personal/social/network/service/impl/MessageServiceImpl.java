package project.personal.social.network.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.social.network.entity.MessageEntity;
import project.personal.social.network.repo.MessageRepository;
import project.personal.social.network.resource.DataMapper;
import project.personal.social.network.service.MessageService;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageRepository messageRepository;

	private final DataMapper mapper = DataMapper.INSTANCE;

	@Override
	public List<MessageRes> getMessages(UUID roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageRes createMessage(MessageReq req) {
		MessageEntity entity = this.mapper.fromRequestToEntity(req);
		entity = this.messageRepository.saveAndFlush(entity);
		return this.mapper.fromEntityToResponse(entity);
	}

	@Override
	public void deleteMessage(UUID id) throws EntityNotFoundException {
		MessageEntity entity = this.findById(id);

		entity.setDeleted(true);
		this.messageRepository.saveAndFlush(entity);
	}

	@Override
	public void updateMessage(UUID id, MessageReq req) throws EntityNotFoundException {
		MessageEntity entity = this.findById(id);

		this.mapper.fromRequestToEntity(entity, req);
		this.messageRepository.saveAndFlush(entity);
	}

	private MessageEntity findById(UUID id) throws EntityNotFoundException {
		Optional<MessageEntity> entityOpt = this.messageRepository.findById(id);
		if (!entityOpt.isPresent())
			throw new EntityNotFoundException(String.format("Message %s is not found", id));

		return entityOpt.get();
	}

}
