package project.personal.shared.common.model.res;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import project.personal.shared.common.constant.Gender;

@Data
@JsonPropertyOrder(value = {
		"firstName",
		"middleName",
		"lastName",
		"dateOfBirth",
		"phoneNumber",
		"email",
		"gender"
})
public class UserInformationRes {

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("email")
	private String email;

	@JsonProperty("gender")
	private Gender gender;

}
