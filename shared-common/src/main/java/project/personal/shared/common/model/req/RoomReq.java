package project.personal.shared.common.model.req;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoomReq {

	@JsonProperty("roomName")
	@NotBlank(message = "Room name can't be empty.")
	private String roomName;

}
