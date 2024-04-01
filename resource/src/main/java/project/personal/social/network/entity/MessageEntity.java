package project.personal.social.network.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@SQLRestriction("IS_DELETED = 'false'")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "room", "documents" })
public class MessageEntity extends BaseEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.UUID)
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
	private RoomEntity room;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = DocumentEntity.class)
	@JoinTable(name = "document_messages", 
			  joinColumns = @JoinColumn(name = "MESSAGES_ID"), 
			  inverseJoinColumns = @JoinColumn(name = "DOCUMENTS_ID"))
	@JsonProperty("documents")
	private List<DocumentEntity> documents = new ArrayList<DocumentEntity>();

}
