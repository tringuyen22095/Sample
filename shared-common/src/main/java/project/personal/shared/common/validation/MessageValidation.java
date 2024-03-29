package project.personal.shared.common.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import project.personal.shared.common.validator.MessageValidator;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MessageValidator.class)
public @interface MessageValidation {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
