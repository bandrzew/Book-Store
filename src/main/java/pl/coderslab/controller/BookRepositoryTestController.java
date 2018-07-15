package pl.coderslab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.BookRepository;

@Controller
@RequestMapping("/bookrepo")
public class BookRepositoryTestController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherDao publisherDao;
	@Autowired
	private AuthorDao authorDao;

	@GetMapping("/all")
	public String showAll(Model model) {
		List<Book> books = this.bookRepository.findAll();
		model.addAttribute("books", books);
		return "bookList";
	}

	@GetMapping("/findbytitle/{title}")
	@ResponseBody
	public String findByTitle(@PathVariable String title) {
		Book foundBook = this.bookRepository.findByTitle(title);
		if (foundBook == null) {
			return "Nie znaleziono";
		} else {
			return foundBook.toString();
		}
	}

	@GetMapping("/findbytitlelike/{toSearch}")
	public String findByTitleLike(Model model, @PathVariable String toSearch) {
		List<Book> books = this.bookRepository.findByTitleLike("%" + toSearch + "%");
		model.addAttribute("books", books);
		return "bookList";
	}

	@GetMapping("/findbypublisher")
	public String findByPublisher(Model model) {
		Publisher publisher = this.publisherDao.findById(2L);
		List<Book> books = this.bookRepository.findByPublisher(publisher);
		model.addAttribute("books", books);
		return "bookList";
	}

	@GetMapping("/findbypublisherid/{id}")
	public String findByPublisherId(@PathVariable Long id, Model model) {
		List<Book> books = this.bookRepository.findByPublisherId(id);
		model.addAttribute("books", books);
		return "bookList";
	}
	
	@GetMapping("/findbyauthor")
	public String findByAuthor(Model model) {
		Author author = this.authorDao.findById(2L);
		List<Book> books = this.bookRepository.findByAuthors(author);
		model.addAttribute("books", books);
		return "bookList";
	}
}
