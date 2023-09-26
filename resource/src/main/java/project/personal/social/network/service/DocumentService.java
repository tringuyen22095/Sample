package project.personal.social.network.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.DocumentReq;
import project.personal.shared.common.model.res.DocumentRes;

public interface DocumentService {

	Page<DocumentRes> pagingByRoomId(UUID roomId, Pageable pageable);

	void updateDocument(UUID docId, DocumentReq req) throws EntityNotFoundException;

}
