package project.personal.shared.common.constant;

import lombok.Getter;

@Getter
public enum MessageType {
	TEXT("TEXT"), VOICE("VOICE"), FILE("FILE");

	private MessageType(String value) {
		this.value = value;
	}

	private String value;
}
