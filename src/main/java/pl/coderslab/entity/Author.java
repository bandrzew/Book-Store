package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import pl.coderslab.validation.IsOver18YO;
import pl.coderslab.validation.IsOverXYO;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@ManyToMany(mappedBy = "authors", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Book> books = new ArrayList<>();
//	@IsOver18YO
//	private int yearOfBirth;
//	@IsOverXYO(age=23)
//	private int authorAge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

//	public int getYearOfBirth() {
//		return yearOfBirth;
//	}
//
//	public void setYearOfBirth(int yearOfBirth) {
//		this.yearOfBirth = yearOfBirth;
//	}
//
//	public int getAuthorAge() {
//		return authorAge;
//	}
//
//	public void setAuthorAge(int authorAge) {
//		this.authorAge = authorAge;
//	}
	
	
}
