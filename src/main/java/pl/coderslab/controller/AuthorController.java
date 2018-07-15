package pl.coderslab.controller;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Publisher;

@Controller
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorDao authorDao;

	@GetMapping("/add")
	@ResponseBody
	public String add() {

		Author sienkiewicz = new Author();
		sienkiewicz.setFirstName("Henryk");
		sienkiewicz.setLastName("Sienkiewicz");
		this.authorDao.save(sienkiewicz);

		Author horstmann = new Author();
		horstmann.setFirstName("Cay S.");
		horstmann.setLastName("Horstmann");
		this.authorDao.save(horstmann);

		Author cornell = new Author();
		cornell.setFirstName("Gary");
		cornell.setLastName("Cornell");
		this.authorDao.save(cornell);

		return "added authors";
	}

	@GetMapping("/list")
	@ResponseBody
	public String list() {
		return this.authorDao.getAll().toString();
	}
	
	@GetMapping("/del/{id}")
	@ResponseBody
	public String del(@PathVariable long id) {
		this.authorDao.delete(this.authorDao.findById(id));
		return this.authorDao.getAll().toString();
	}
	
	@GetMapping("/{id}/books")
	@ResponseBody
	@Transactional
	public String books(@PathVariable long id) {
		Author author = this.authorDao.findById(id);
		Hibernate.initialize(author.getBooks());
		return author.getBooks().toString();
	}
}
