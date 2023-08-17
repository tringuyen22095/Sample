package project.personal.social.network.entity.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

	@Column(name = "IS_DELETED", nullable = false)
	@JsonProperty("isDeleted")
	private boolean isDeleted = false;

	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("createdOn")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date createdOn;

	@Column(name = "CREATED_BY")
	@JsonProperty("createdBy")
	private UUID createdBy;

	@Column(name = "UPDATED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("updatedOn")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date updatedOn;

	@Column(name = "UPDATED_BY")
	@JsonProperty("updatedBy")
	private UUID updatedBy;
}
