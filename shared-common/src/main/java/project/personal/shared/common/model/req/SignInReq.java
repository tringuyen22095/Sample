package project.personal.shared.common.model.req;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(value = {
		"username",
		"password"
})
public class SignInReq {

	@NotNull
	@Size(min = 6)
	@JsonProperty("username")
	private String username;

	@NotNull
	@Size(min = 6, max = 16)
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,16}", message = "Length must be from 6 to 16 characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	@JsonProperty("password")
	private String password;

}
