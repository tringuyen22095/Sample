package project.personal.social.network.resource;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.MessageRes;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;
import project.personal.social.network.entity.MessageEntity;
import project.personal.social.network.entity.RoomEntity;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DataMapper {

	DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);

	void fromRequestToEntity(@MappingTarget RoomEntity entity, RoomReq req);

	RoomEntity fromRequestToEntity(RoomReq req);

	RoomRes fromEntityToResponse(RoomEntity entity);

	RoomDetailRes fromEntityToResponseDetail(RoomEntity entity);

	@Mapping(source = "roomId", target = "room.id")
	void fromRequestToEntity(@MappingTarget MessageEntity entity, MessageReq req);

	@Mapping(source = "roomId", target = "room.id")
	MessageEntity fromRequestToEntity(MessageReq req);

	MessageRes fromEntityToResponse(MessageEntity entity);

}
