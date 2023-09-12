package project.personal.shared.common.model.res;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.personal.shared.common.constant.MessageType;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class MessageRes {

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("content")
	private String content;

	@JsonProperty("type")
	private MessageType type;

	@JsonProperty("createdOn")
	private Date createdOn;

	@JsonProperty("createdBy")
	private UUID createdBy;

	@JsonProperty("updatedOn")
	private Date updatedOn;

	@JsonProperty("updatedBy")
	private UUID updatedBy;

	@JsonProperty("documents")
	private List<DocumentRes> documents;

}
