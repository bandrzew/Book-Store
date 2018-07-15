package pl.coderslab.controller;

import java.util.ArrayList;
import java.util.List;

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
import pl.coderslab.model.PersonDTO;

@Controller
@RequestMapping("/personDetail")
public class PersonDetailController {
	@GetMapping("/form")
	public String formTaglib(Model m) {
		m.addAttribute("personDto", new PersonDTO());

		return "personDtoForm";
	}
	
	@PostMapping("/form")
	@ResponseBody
	public String formTaglibPost(@ModelAttribute PersonDTO personDto) {
		return personDto.toString();
	}
	
	@ModelAttribute("countries")
	public List<String> getCountries(){
		List<String> list = new ArrayList<>();
		list.add("Poland");
		list.add("France");
		list.add("Malaysia");
		
		return list;
	}
	
	@ModelAttribute("skills")
	public List<String> getSkills(){
		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("JS");
		list.add("SQL");
		list.add("Python");
		
		return list;
	}
	
	@ModelAttribute("hobbies")
	public List<String> getHobbies(){
		List<String> list = new ArrayList<>();
		list.add("Music");
		list.add("Travel");
		list.add("Bike");
		list.add("Snooker");
		
		return list;
	}
}
