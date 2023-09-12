package project.personal.shared.common.model.res;

import java.sql.Blob;
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
public class DocumentRes {

	@JsonProperty("id")
	private UUID id;

	@JsonProperty("bData")
	private Blob bData;

	@JsonProperty("fileType")
	private String fileType;

	@JsonProperty("fileName")
	private String fileName;

}
