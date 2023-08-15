package project.personal.social.network.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import project.personal.social.network.entity.base.BaseEntity;
import project.personal.social.network.entity.base.EntityToPersistListener;

@Entity
@EntityListeners(EntityToPersistListener.class)
@Table(name = "ROOM")
@Data
public class RoomEntity extends BaseEntity {

	@Id
	@Column(name = "ID")
	private UUID id;

	@Column(name = "ROOM_NAME")
	private String roomName;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<MessageEntity> messages;

	@ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccountEntity> accounts;

}
