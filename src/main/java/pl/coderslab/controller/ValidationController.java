package pl.coderslab.controller;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.validation.GroupTest;
import pl.coderslab.validation.OurGroup;

@Controller
@RequestMapping("/valid")
public class ValidationController {
	@Autowired
	private Validator validator;

	@GetMapping("/validate")
	public String validate(Model m) {
		Book book = new Book();
		Set<ConstraintViolation<Book>> violations = this.validator.validate(book);
		if (violations.isEmpty()) {
			// można zapisać do bazy
			return "noErrors";
		} else {
			m.addAttribute("errors", violations);
			return "showErrors";
		}
	}

	@GetMapping("/ok")
	@ResponseBody
	public String ok() {
		Book book = new Book();
		book.getAuthors().add(new Author());
		book.setDescription("dlkjl");
		book.setPages(3);
		book.setPublisher(new Publisher());
		book.setRating(new BigDecimal("3"));
		book.setTitle("W pustyni i w puszczy");

		Set<ConstraintViolation<Book>> violations = this.validator.validate(book);
		return violations.toString();
	}
	
//	@GetMapping("/over18yotest")
//	@ResponseBody
//	public String customValidator() {
//		Author author = new Author();
//		author.setYearOfBirth(2015);
//		
//		Set<ConstraintViolation<Author>> violations = this.validator.validate(author);
//		return violations.toString();
//	}
//	
//	@GetMapping("/overxyotest")
//	@ResponseBody
//	public String customValidator2() {
//		Author author = new Author();
//		author.setAuthorAge(20);
//		
//		Set<ConstraintViolation<Author>> violations = this.validator.validate(author);
//		return violations.toString();
//	}
	
	@GetMapping("/grouptest")
	public String grouptest(Model m) {
		GroupTest groupTest = new GroupTest();
		groupTest.setTestDefaultGroup(5);
		
		Set<ConstraintViolation<GroupTest>> violations = this.validator.validate(groupTest);
		m.addAttribute("errors", violations);
		
		return "showErrors";
	}
	
	@GetMapping("/grouptestOur")
	public String grouptestOur(Model m) {
		GroupTest groupTest = new GroupTest();
		groupTest.setTestDefaultGroup(5);
		groupTest.setTestOurGroup(5);
		
		// poniżej waliduję tylko warunki, które są w grupie OurGroup
		Set<ConstraintViolation<GroupTest>> violations = this.validator.validate(groupTest, OurGroup.class);
		m.addAttribute("errors", violations);
		
		return "showErrors";
	}
	
	@GetMapping("/grouptestBoth")
	public String grouptestBoth(Model m) {
		GroupTest groupTest = new GroupTest();
		groupTest.setTestDefaultGroup(5);
		groupTest.setTestOurGroup(5);
		groupTest.setTestBothGroups(5);
		
		// poniżej waliduję tylko warunki, które są w grupie OurGroup
		Set<ConstraintViolation<GroupTest>> violations = this.validator.validate(groupTest, OurGroup.class);
		m.addAttribute("errors", violations);
		
		return "showErrors";
	}
}
