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
import project.personal.shared.common.model.req.DocumentReq;
import project.personal.shared.common.model.res.DocumentRes;
import project.personal.social.network.entity.DocumentEntity;
import project.personal.social.network.repo.DocumentRepository;
import project.personal.social.network.resource.DataMapper;
import project.personal.social.network.service.DocumentService;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

	private static final Logger _log = LoggerFactory.getLogger(DocumentServiceImpl.class);

	private final DocumentRepository documentRepository;

	private final DataMapper mapper = DataMapper.INSTANCE;

	@Override
	public Page<DocumentRes> pagingByRoomId(UUID roomId, Pageable pageable) {
		Page<DocumentEntity> paging = this.documentRepository.pagingByRoomId(roomId, pageable);
		List<DocumentRes> lstMsg = paging.getContent().stream().map(mapper::fromEntityToResponse)
				.collect(Collectors.toList());
		return new PageImpl<DocumentRes>(lstMsg, pageable, paging.getTotalElements());
	}

	@Override
	public void updateDocument(UUID docId, DocumentReq req) throws EntityNotFoundException {
		DocumentEntity entity = this.findById(docId);

		this.mapper.fromRequestToExistEntity(entity, req);
		this.documentRepository.saveAndFlush(entity);
	}

	private DocumentEntity findById(UUID docId) throws EntityNotFoundException {
		_log.info("Find document by {}", docId);
		Optional<DocumentEntity> entityOpt = this.documentRepository.findById(docId);
		if (!entityOpt.isPresent())
			throw new EntityNotFoundException(String.format("Document %s is not found", docId));

		return entityOpt.get();
	}

}
