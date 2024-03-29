package project.personal.shared.common.model.req;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.personal.shared.common.constant.MessageType;
import project.personal.shared.common.validation.EnumValidation;
import project.personal.shared.common.validation.MessageValidation;

@Getter
@Setter
@NoArgsConstructor
@MessageValidation
public class MessageReq {

	@JsonProperty("content")
	private String content;
	
	@JsonProperty("documents")
	private List<DocumentReq> documents;

	@JsonProperty("type")
	@NotNull(message = "Type can't be empty.")
	@EnumValidation(enumClass = MessageType.class, message = "Type valid isn't valid.")
	private String type;
	
	@JsonProperty("roomId")
	@NotNull(message = "Room id can't be empty.")
	private UUID roomId;

}
