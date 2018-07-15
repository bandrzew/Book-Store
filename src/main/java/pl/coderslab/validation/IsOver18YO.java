package pl.coderslab.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = IsOver18YOValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsOver18YO {
	String message() default "Dozwolone od lat osiemnastu!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}