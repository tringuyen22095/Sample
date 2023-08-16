package project.personal.shared.common.model.res;

import java.sql.Blob;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import project.personal.shared.common.constant.MessageType;

@Data
public class MessageRes {

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("content")
	private String content;

	@JsonProperty("bData")
	private Blob bData;

	@JsonProperty("type")
	private MessageType type;

}
