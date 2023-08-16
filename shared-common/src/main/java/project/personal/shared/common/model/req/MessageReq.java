package project.personal.shared.common.model.req;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import project.personal.shared.common.constant.MessageType;

@Data
public class MessageReq {

	@JsonProperty("content")
	@NotBlank(message = "Content can't be empty.")
	private String content;

	@JsonProperty("type")
	@NotNull(message = "Type can't be empty.")
	private MessageType type;
	
	@JsonProperty("roomId")
	@NotNull(message = "Room id can't be empty.")
	private UUID roomId;

}
