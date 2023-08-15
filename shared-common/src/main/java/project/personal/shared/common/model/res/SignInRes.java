package project.personal.shared.common.model.res;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignInRes {

	@JsonProperty("token")
	private String token;

	@JsonProperty("expiredTime")
	private LocalDateTime expiredTime;

}
