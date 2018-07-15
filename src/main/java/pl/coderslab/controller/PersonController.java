package pl.coderslab.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.PersonDao;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

@Controller
@Transactional
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonDao personDao;
	
	@GetMapping("/time")
	@ResponseBody
	public String time() {
		Person p = new Person();
		p.setDateTime(new Date());
		this.personDao.save(p);
		return "time";
	}

	@GetMapping("/addAndCreateDependencies")
	@ResponseBody
	public String addAndCreate() {
		Person person = new Person();
		person.setEmail("person@mail.pl");
		person.setLogin("person");
		person.setPassword("password");

		PersonDetails personDetails = new PersonDetails();
		personDetails.setCity("Wroclove");
		personDetails.setFirstName("Bernard");
		personDetails.setLastName("Bernardowicz");
		personDetails.setStreet("Rynek");
		personDetails.setStreetNumber("1");

		person.setPersonDetails(personDetails);

		this.personDao.save(person);

		return "added person";
	}

	@GetMapping("/form")
	public String form() {
		return "personForm";
	}
	
	@PostMapping("/form")
	@ResponseBody
	public String formPost(@RequestParam String login,
			@RequestParam String password,
			@RequestParam String email) {
		Person person = new Person();
		person.setLogin(login);
		person.setPassword(password);
		person.setEmail(email);
		
		this.personDao.save(person);
		
		return person.toString();
	}
	
	@GetMapping("/formTaglib")
	public String formTaglib(Model m) {
		m.addAttribute("person", new Person());
		
		return "personFormTaglib";
	}
	
	@PostMapping("/formTaglib")
	@ResponseBody
	public String formTaglibPost(@ModelAttribute Person person) {
		this.personDao.save(person);
		
		return person.toString();
	}
}
