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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import project.personal.social.network.entity.base.BaseEntity;
import project.personal.social.network.entity.base.EntityToPersistListener;

@Entity
@EntityListeners(EntityToPersistListener.class)
@Table(name = "DOCUMENT")
@SQLRestriction("IS_DELETED = 'false'")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "messages" })
public class DocumentEntity extends BaseEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.UUID)
	@JsonProperty("id")
	private UUID id;

	@Column(name = "FILE_TYPE")
	@JsonProperty("fileType")
	private String fileType;

	@Column(name = "FILE_NAME")
	@JsonProperty("fileName")
	private String fileName;

	@ManyToMany(mappedBy = "documents", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty("messages")
	private List<MessageEntity> messages = new ArrayList<MessageEntity>();

}
