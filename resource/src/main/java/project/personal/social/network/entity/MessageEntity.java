package project.personal.social.network.entity;

import java.awt.TrayIcon.MessageType;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import project.personal.social.network.entity.base.BaseEntity;
import project.personal.social.network.entity.base.EntityToPersistListener;

@Entity
@EntityListeners(EntityToPersistListener.class)
@Table(name = "MESSAGE")
@Data
public class MessageEntity extends BaseEntity {

	@Id
	@Column(name = "ID")
	private UUID id;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "TYPE")
	private MessageType type;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOM_ID")
	private RoomEntity room;

}
