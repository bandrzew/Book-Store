package pl.coderslab.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsOverXYOValidator implements ConstraintValidator<IsOverXYO, Integer> {
	private int minimumAge;
	
	@Override
	public void initialize(IsOverXYO constraintAnnotation) {
		this.minimumAge = constraintAnnotation.age();
	}

	@Override
	public boolean isValid(Integer ageOfPerson, ConstraintValidatorContext context) {
		return ageOfPerson > this.minimumAge;
	}
}