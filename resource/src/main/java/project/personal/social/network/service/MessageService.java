package project.personal.social.network.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;

public interface MessageService {

	Page<MessageRes> getMessages(UUID roomId, Pageable pageable) throws EntityNotFoundException;

	MessageRes createMessage(MessageReq req) throws EntityNotFoundException;

	void deleteMessage(UUID id) throws EntityNotFoundException;

	void updateMessage(UUID id, MessageReq req) throws EntityNotFoundException;

}
