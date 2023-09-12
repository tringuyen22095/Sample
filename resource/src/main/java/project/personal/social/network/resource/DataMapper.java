package project.personal.social.network.resource;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import project.personal.shared.common.model.req.DocumentReq;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.DocumentRes;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;
import project.personal.social.network.entity.DocumentEntity;
import project.personal.social.network.entity.MessageEntity;
import project.personal.social.network.entity.RoomEntity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DataMapper {

	DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);

	/* Room */
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "messages", ignore = true)
	void fromRequestToEntity(@MappingTarget RoomEntity entity, RoomReq req);

	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "messages", ignore = true)
	RoomEntity fromRequestToExistEntity(RoomReq req);

	RoomRes fromEntityToResponse(RoomEntity entity);

	RoomDetailRes fromEntityToResponseDetail(RoomEntity entity);

	/* Room */

	/* Message */

	@Mapping(source = "roomId", target = "room.id")
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "documents", ignore = true)
	@Mapping(target = "id", ignore = true)
	void fromRequestToExistEntity(@MappingTarget MessageEntity entity, MessageReq req);

	@Mapping(source = "roomId", target = "room.id")
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	MessageEntity fromRequestToEntity(MessageReq req);

	@Mapping(conditionExpression = "java(!MessageType.TEXT.equals(entity.getType()))", target = "documents")
	MessageRes fromEntityToResponse(MessageEntity entity);

	/* Message */

	/* Document */

	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "messages", ignore = true)
	DocumentEntity fromRequestToEntity(DocumentReq req);

	DocumentRes fromEntityToResponse(DocumentEntity req);

	/* Document */

}
