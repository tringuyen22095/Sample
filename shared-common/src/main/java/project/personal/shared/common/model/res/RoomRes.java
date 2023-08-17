package project.personal.shared.common.model.res;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class RoomRes {

	@JsonProperty("id")
	private String id;

	@JsonProperty("roomName")
	private String roomName;

	@JsonProperty("createdOn")
	private Date createdOn;

	@JsonProperty("createdBy")
	private UUID createdBy;

	@JsonProperty("updatedOn")
	private Date updatedOn;

	@JsonProperty("updatedBy")
	private UUID updatedBy;

}
