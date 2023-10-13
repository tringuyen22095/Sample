package project.personal.social.network.resource;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
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

	@Mapping(target = "fileType", ignore = true)
	@Mapping(target = "fileName", ignore = true)
	@Mapping(target = "messages", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "BData", source = "BData", qualifiedByName = "byteArrayToBlob", conditionExpression = ("java(req.getBData() != null)"))
	void fromRequestToExistEntity(@MappingTarget DocumentEntity entity, DocumentReq req);

	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "messages", ignore = true)
	@Mapping(target = "BData", source = "BData", qualifiedByName = "byteArrayToBlob", conditionExpression = ("java(req.getBData() != null)"))
	DocumentEntity fromRequestToEntity(DocumentReq req);

	@Mapping(target = "BData", source = "BData", qualifiedByName = "blobToByteArray", conditionExpression = ("java(req.getBData() != null)"))
	DocumentRes fromEntityToResponse(DocumentEntity req);

	/* Document */

	@Named("byteArrayToBlob")
	public static Blob byteArrayToBlob(byte[] by) throws Exception {
		return new SerialBlob(by);
	}

	@Named("blobToByteArray") 
    public static byte[] byteArrayToBlob(Blob blob) throws Exception {
        return blob.getBinaryStream().readAllBytes();
    }

}
