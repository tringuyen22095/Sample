package project.personal.shared.common.model.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoomDetailRes extends RoomRes {

	@JsonProperty("messages")
	private List<MessageRes> messages;

	@JsonProperty("accounts")
	private List<AccountRes> accounts;

}
