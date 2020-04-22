package com.genpact.Library.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.genpact.Library.model.Book;
import com.genpact.Library.model.Library;
import com.genpact.Library.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;
	
	@MockBean
	private BookRepository bookRepository;
	
	@Test
	public void createBook() {
		Library library = new Library();
		library.setId((long) 1);
		library.setName("Central Library");
		
		Book book = new Book();
		book.setId((long) (4));
		book.setBookName("Java and J2EE");
		book.setAuthorName("XYZ");
		book.setLibray(library);
		
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		assertThat(bookService.saveBookData(book)).isEqualTo(book);
	}
	
	@Test
	public void getAllBooks() {
		Library library = new Library();
		library.setId((long) 1);
		library.setName("Central Library");
		
		Book b1 = new Book();
		b1.setId((long) 1);
		b1.setBookName("Rich Dad Poor Dad");
		b1.setLibray(library);
		b1.setAuthorName("XYZ");
		
		Book b2 = new Book();
		b2.setId((long)2);
		b2.setBookName("Goblet of Fire");
		b2.setLibray(library);
		b2.setAuthorName("XYZ");
		
		Book b3 = new Book();
		b3.setId((long) 3);
		b3.setBookName("CCNA Basics and Advanced Topics");
		b3.setLibray(library);
		b3.setAuthorName("XYZ");
		
		List<Book> books = new LinkedList<>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		
		Mockito.when(bookRepository.findAll()).thenReturn(books);
		assertThat(bookService.getAllBooks()).isEqualTo(books);
		
	}
	
	@Test
	public void getBooksByLibraryName() {
		Library library = new Library();
		library.setId((long) 1);
		library.setName("Central Library");
		
		Book b1 = new Book();
		b1.setId((long) 1);
		b1.setBookName("Rich Dad Poor Dad");
		b1.setLibray(library);
		b1.setAuthorName("XYZ");
		
		Book b2 = new Book();
		b2.setId((long)2);
		b2.setBookName("Goblet of Fire");
		b2.setLibray(library);
		b2.setAuthorName("XYZ");
		
		List<Book> books = new LinkedList<>();
		books.add(b1);
		books.add(b2);
		
		Mockito.when(bookRepository.findByLibraryId(library.getId())).thenReturn(books);
		assertThat(bookService.getBooksByLibraryName("Central Library")).isEqualTo(books);
	
	}
	
	@Test
	public void updateBook() {
		Library library = new Library();
		library.setId((long) 1);
		library.setName("Central Library");
		
		Optional<Book> book = Optional.of(new Book());
		book.get().setId((long) (4));
		book.get().setBookName("Java and J2EE");
		book.get().setLibray(library);
		book.get().setAuthorName("XYZ");
		
		Mockito.when(bookRepository.findById((long) 4)).thenReturn(book);
		book.get().setBookName("Java J2EE and Oracle");
	
		Mockito.when(bookRepository.save(book.get())).thenReturn(book.get());
		assertThat(bookService.saveBookData(book.get())).isEqualTo(book.get());
	}
	
}
