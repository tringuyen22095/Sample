package project.personal.shared.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import project.personal.shared.common.validator.EnumValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValidation {

	Class<? extends Enum<?>> enumClass();

	String message() default "Value is not valid.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
