package project.personal.shared.common.model.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.personal.shared.common.validation.MessageValidation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MessageValidation
@Builder
public class DocumentReq {

	@JsonProperty("bData")
	@NotNull(message = "Data can't be empty.")
	private byte[] bData;

	@JsonProperty("fileType")
	@NotEmpty(message = "File type can't be empty.")
	private String fileType;

	@JsonProperty("fileName")
	@NotEmpty(message = "Type can't be empty.")
	private String fileName;

}
