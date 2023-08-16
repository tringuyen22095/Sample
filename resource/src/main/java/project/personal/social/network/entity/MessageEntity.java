package project.personal.social.network.entity;

import java.sql.Blob;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import project.personal.shared.common.constant.MessageType;
import project.personal.social.network.entity.base.BaseEntity;
import project.personal.social.network.entity.base.EntityToPersistListener;

@Entity
@EntityListeners(EntityToPersistListener.class)
@Table(name = "MESSAGE")
@Data
@Where(clause = "IS_DELETED = 0")
public class MessageEntity extends BaseEntity {

	@Id
	@Column(name = "ID")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@JsonProperty("id")
	private UUID id;

	@Column(name = "CONTENT")
	@JsonProperty("content")
	private String content;

	@Column(name = "BDATA")
	@JsonProperty("bData")
	@Lob
	private Blob bData;

	@Column(name = "TYPE")
	@JsonProperty("type")
	private MessageType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOM_ID")
	@JsonProperty("room")
	private RoomEntity room;

}
