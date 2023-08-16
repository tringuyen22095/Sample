package project.personal.social.network.service;

import java.util.List;
import java.util.UUID;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;

public interface MessageService {

	List<MessageRes> getMessages(UUID roomId);

	MessageRes createMessage(MessageReq req);

	void deleteMessage(UUID id) throws EntityNotFoundException;

	void updateMessage(UUID id, MessageReq req) throws EntityNotFoundException;

}
