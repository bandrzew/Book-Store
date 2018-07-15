package pl.coderslab.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private PublisherDao publisherDao;
	@Autowired
	private AuthorDao authorDao;
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ServletContext servletContext;

	@GetMapping("/addWithDependenciesFromDb")
	@ResponseBody
	public String add() {
		Book java = new Book();
		java.setTitle("Java. Podstawy.");
		java.setRating(new BigDecimal("8.0"));
		java.setDescription("Książka o programowaniu w Javie");
		Author horstmann = this.authorDao.findById(2L);
		Author cornell = this.authorDao.findById(3L);
		java.getAuthors().add(horstmann);
		java.getAuthors().add(cornell);

		Publisher pwn = this.publisherDao.findById(2L);
		java.setPublisher(pwn);

		this.bookDao.save(java);

		return "added java";
	}

	@GetMapping("/addAndCreateDependencies")
	@ResponseBody
	public String addAndCreate() {
		Book szkice = new Book();
		szkice.setTitle("Szkice piórkiem");
		szkice.setRating(new BigDecimal("8.0"));
		szkice.setDescription("Dziennik z XX wieku");

		Author bobkowski = new Author();
		bobkowski.setFirstName("Andrzej");
		bobkowski.setLastName("Bobkowski");

		szkice.getAuthors().add(bobkowski);

		Publisher ossolineum = new Publisher();
		ossolineum.setName("Ossolineum");

		szkice.setPublisher(ossolineum);

		this.bookDao.save(szkice);

		return "added szkice";
	}

	@GetMapping("/list")
	@ResponseBody
	public String list() {
		return this.bookDao.getAll().toString();
	}

	@GetMapping("/del/{id}")
	@ResponseBody
	public String del(@PathVariable long id) {
		Book book = this.bookDao.findById(id);
		book.getAuthors().clear();
		book.setPublisher(null);
		this.bookDao.delete(book);
		return this.bookDao.getAll().toString();
	}

	@GetMapping("/{id}/update")
	@ResponseBody
	public String update(@PathVariable long id) {
		Book book = this.bookDao.findById(id);
		book.setDescription("nowy opis");
		return this.bookDao.getAll().toString();
	}

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("book", new Book());
		return "addBookForm";
	}

	@PostMapping("/form")
	public String formPost(@Valid Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "addBookForm";
		}
		this.bookDao.save(book);
		return "redirect:/book/listJsp";
	}

	@GetMapping("/listJsp")
	public String listJsp() {
		return "bookList";
	}

	@ModelAttribute("authors")
	public List<Author> getAuthors() {
		return this.authorDao.getAll();
	}

	@ModelAttribute("publishers")
	public List<Publisher> getPublishers() {
		return this.publisherDao.getAll();
	}

	@ModelAttribute("books")
	public List<Book> getBooks() {
		return this.bookDao.getAll();
	}
}
