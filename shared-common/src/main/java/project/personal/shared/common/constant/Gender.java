package project.personal.shared.common.constant;

import lombok.Getter;

@Getter
public enum Gender {
	MALE("MALE"), FEMALE("FEMALE");

	private Gender(String value) {
		this.value = value;
	}

	private String value;
}
