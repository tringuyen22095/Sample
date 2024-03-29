package project.personal.shared.common.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomReq {

	@JsonProperty("roomName")
	@NotBlank(message = "Room name can't be empty.")
	private String roomName;

}
