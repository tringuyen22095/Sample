package project.personal.shared.common.model.res;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 7399057810695038613L;

	@JsonProperty("errorCode")
	private int errorCode;

	@JsonProperty("errorMsg")
	private String errorMsg;

	@JsonProperty("invalidFields")
	private Map<String, String> invalidFields;

}
