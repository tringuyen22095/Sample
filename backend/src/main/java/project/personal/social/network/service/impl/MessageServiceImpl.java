package project.personal.social.network.service.impl;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import project.personal.shared.client.resource.MessageFeignClient;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.social.network.service.MessageService;

@Service
@Primary
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageFeignClient messageFeignClient;

	@Override
	public ResponseEntity<Page<MessageRes>> getMessage(UUID roomId, int page, int size, Direction direction,
			String... properties) throws EntityNotFoundException {
		return this.messageFeignClient.getMessage(roomId, page, size, direction, properties);
	}

	@Override
	public ResponseEntity<MessageRes> createMessage(MessageReq req)
			throws EntityNotFoundException, InterruptedException {
		ResponseEntity<MessageRes> response = this.messageFeignClient.createMessage(req);
		return response;
	}

	@Override
	public ResponseEntity<Void> deleteMessage(UUID id) throws EntityNotFoundException {
		return this.messageFeignClient.deleteMessage(id);
	}

	@Override
	public ResponseEntity<Void> updateRoom(UUID id, MessageReq req) throws EntityNotFoundException {
		return this.messageFeignClient.updateRoom(id, req);
	}

}
