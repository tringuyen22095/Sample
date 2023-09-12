package project.personal.social.network.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;

public interface MessageService {

	ResponseEntity<Page<MessageRes>> getMessage(UUID roomId, int page, int size, Direction direction,
			String... properties) throws EntityNotFoundException;

	ResponseEntity<MessageRes> createMessage(MessageReq req) throws EntityNotFoundException;

	ResponseEntity<Void> deleteMessage(UUID id) throws EntityNotFoundException;

	ResponseEntity<Void> updateRoom(UUID id, MessageReq req) throws EntityNotFoundException;

}
