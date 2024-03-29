package project.personal.shared.common.validator;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.personal.shared.common.validation.EnumValidation;

public class EnumValidator implements ConstraintValidator<EnumValidation, String> {

	List<String> valueList = null;

	@Override
	@SuppressWarnings("rawtypes")
	public void initialize(EnumValidation constraintAnnotation) {
		valueList = new ArrayList<String>();
		Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

		for (Enum enumVal : enumClass.getEnumConstants()) {
			valueList.add(enumVal.toString());
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return this.valueList.contains(value);
	}

}