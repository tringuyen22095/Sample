package project.personal.shared.common.model.res;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoomRes {

	@JsonProperty("id")
	private String id;

	@JsonProperty("roomName")
	private String roomName;

	@JsonProperty("createdOn")
	private Date createdOn;

	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("updatedOn")
	private Date updatedOn;

	@JsonProperty("updatedBy")
	private String updatedBy;

}
