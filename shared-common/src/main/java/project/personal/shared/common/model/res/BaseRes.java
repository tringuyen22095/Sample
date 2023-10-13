package project.personal.shared.common.model.res;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRes {

	@JsonProperty("createdOn")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdOn;

	@JsonProperty("createdBy")
	private UUID createdBy;

	@JsonProperty("updatedOn")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedOn;

	@JsonProperty("updatedBy")
	private UUID updatedBy;
}
