package pl.coderslab.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import pl.coderslab.validation.Proposition;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, groups = { Default.class, Proposition.class }, message = "Tytuł musi mieć przynajmniej 5 znaków")
	private String title;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@NotEmpty(message = "Autor musi być dodany")
	private List<Author> authors = new ArrayList<>();

	@Column(precision = 4, scale = 2)
	@NotNull
	@Min(1)
	@Max(10)
	private BigDecimal rating;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@NotNull
	private Publisher publisher;

	@Column(columnDefinition = "TEXT")
	@NotBlank(groups = { Proposition.class })
	@Size(max = 600, groups = { Default.class, Proposition.class })
	private String description;

	@Min(2)
	private int pages;

	private boolean proposition;

	public boolean isProposition() {
		return proposition;
	}

	public void setProposition(boolean proposition) {
		this.proposition = proposition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", rating=" + rating + ", publisher=" + publisher
				+ ", description=" + description + "]";
	}
}
