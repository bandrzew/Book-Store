package pl.coderslab.controller;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
	@Autowired
	private PublisherDao publisherDao;

	@GetMapping("/add")
	@ResponseBody
	public String add() {
		Publisher ossolineum = new Publisher();
		ossolineum.setName("Ossolineum");
		this.publisherDao.save(ossolineum);

		Publisher pwn = new Publisher();
		pwn.setName("PWN");
		this.publisherDao.save(pwn);

		return "added publishers";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public String list() {
		return this.publisherDao.getAll().toString();
	}
	
	@GetMapping("/{id}/books")
	@ResponseBody
	@Transactional
	public String books(@PathVariable long id) {
		Publisher pub = this.publisherDao.findById(id);
		Hibernate.initialize(pub.getBooks());
		return pub.getBooks().toString();
	}
}
