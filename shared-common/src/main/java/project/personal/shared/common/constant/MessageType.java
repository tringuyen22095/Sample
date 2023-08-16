package project.personal.shared.common.constant;

import lombok.Getter;

@Getter
public enum MessageType {
	TEXT("TEXT"), AUDIO("AUDIO"), FILE("FILE"), IMAGE("IMAGE");

	private MessageType(String value) {
		this.value = value;
	}

	private String value;
}
