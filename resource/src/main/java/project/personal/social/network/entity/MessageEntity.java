package project.personal.social.network.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import project.personal.shared.common.constant.MessageType;
import project.personal.social.network.entity.base.BaseEntity;
import project.personal.social.network.entity.base.EntityToPersistListener;

@Entity
@EntityListeners(EntityToPersistListener.class)
@Table(name = "MESSAGE")
@Where(clause = "IS_DELETED = 0")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "room", "documents" })
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

	@Column(name = "TYPE")
	@JsonProperty("type")
	private MessageType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROOM_ID")
	@JsonProperty("room")
	@Where(clause = "1 = 1")
	private RoomEntity room;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = DocumentEntity.class)
	@JoinTable(name = "document_messages", 
			  joinColumns = @JoinColumn(name = "MESSAGES_ID"), 
			  inverseJoinColumns = @JoinColumn(name = "DOCUMENTS_ID"))
	@JsonProperty("documents")
	private List<DocumentEntity> documents = new ArrayList<DocumentEntity>();

}
