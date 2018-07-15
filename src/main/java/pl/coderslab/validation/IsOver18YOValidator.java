package pl.coderslab.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsOver18YOValidator implements ConstraintValidator<IsOver18YO, Integer> {
	@Override
	public void initialize(IsOver18YO constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext context) {
		int currentYear = LocalDate.now().getYear();
		return (currentYear - yearOfBirth) > 18;
	}
}