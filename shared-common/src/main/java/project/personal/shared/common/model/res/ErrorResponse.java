package project.personal.shared.common.model.res;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(value = Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 7399057810695038613L;

	@JsonProperty("errorCode")
	private int errorCode;

	@JsonProperty("errorMsg")
	private String errorMsg;

	@JsonProperty("errorMsgs")
	private List<String> errorMsgs;

	@JsonProperty("invalidFields")
	private Map<String, String> invalidFields;

}
