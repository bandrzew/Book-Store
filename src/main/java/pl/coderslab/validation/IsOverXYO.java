package pl.coderslab.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = IsOverXYOValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsOverXYO {
	int age();
	String message() default "Jesteś za młody!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}