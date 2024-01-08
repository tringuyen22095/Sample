package project.personal.social.network.resource;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.mapstruct.AfterMapping;
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
	abstract void fromRequestToEntity(@MappingTarget RoomEntity entity, RoomReq req);

	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "messages", ignore = true)
	abstract RoomEntity fromRequestToExistEntity(RoomReq req);

	abstract RoomRes fromEntityToResponse(RoomEntity entity);

	abstract RoomDetailRes fromEntityToResponseDetail(RoomEntity entity);

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
	abstract void fromRequestToExistEntity(@MappingTarget MessageEntity entity, MessageReq req);

	@Mapping(source = "roomId", target = "room.id")
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	abstract MessageEntity fromRequestToEntity(MessageReq req);

	@Mapping(conditionExpression = "java(!MessageType.TEXT.equals(entity.getType()))", target = "documents")
	abstract MessageRes fromEntityToResponse(MessageEntity entity);

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
	abstract void fromRequestToExistEntity(@MappingTarget DocumentEntity entity, DocumentReq req);

	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "createdOn", ignore = true)
	@Mapping(target = "updatedBy", ignore = true)
	@Mapping(target = "updatedOn", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "messages", ignore = true)
	@Mapping(target = "BData", source = "BData", qualifiedByName = "byteArrayToBlob", conditionExpression = ("java(req.getBData() != null)"))
	abstract DocumentEntity fromRequestToEntity(DocumentReq req);

	@Mapping(target = "BData", source = "BData", qualifiedByName = "blobToByteArray", conditionExpression = ("java(req.getBData() != null)"))
	abstract DocumentRes fromEntityToResponse(DocumentEntity req);

	/* Document */

	@Named("byteArrayToBlob")
	public static Blob byteArrayToBlob(byte[] by) throws Exception {
		return new SerialBlob(by);
	}

	@Named("blobToByteArray")
	public static byte[] byteArrayToBlob(Blob blob) throws Exception {
		return blob.getBinaryStream().readAllBytes();
	}

	@AfterMapping
    default void afterMapping(MessageReq dto, @MappingTarget RoomEntity toUpdate) {
    }

//	protected static class MappingContext {
//		private final RoomEntity room;
//
//		public MappingContext(RoomEntity room) {
//			this.room = room;
//		}
//
//		@ObjectFactory
//		public List<MessageEntity> get10LatestMessage() {
//			if (CollectionUtils.isNotEmpty(this.room.getMessages())) {
//				return new ArrayList<MessageEntity>();
//			}
//			return this.room.getMessages().stream().sorted((m1, m2) -> {
//				Date d1 = m1.getUpdatedOn() == null ? m1.getUpdatedOn() : m1.getCreatedOn();
//				Date d2 = m2.getUpdatedOn() == null ? m2.getUpdatedOn() : m2.getCreatedOn();
//				return d1.compareTo(d2);
//			}).limit(10).collect(Collectors.toList());
//		}
//	}

}
