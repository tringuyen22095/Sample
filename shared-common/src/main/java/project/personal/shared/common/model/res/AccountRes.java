package project.personal.shared.common.model.res;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountRes {

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("username")
	private String username;

}
