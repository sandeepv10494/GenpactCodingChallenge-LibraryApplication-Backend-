package com.genpact.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.Library.model.Book;
import com.genpact.Library.model.Library;
import com.genpact.Library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryService libraryService;
	
	public List<Book> getAllBooks(){
		return this.bookRepository.findAll();
	}
	
	public List<Book> getBooksByLibraryName(String name){
		Optional<Library> library = this.libraryService.getLibraryByName(name);
        return this.bookRepository.findByLibraryId(library.get().getId());
	}
	
	public Book saveBookData(Book book) {
		Book newBook = this.bookRepository.save(book);
		return newBook;
	}
	
	public Book updateBook(Long bookId, Book book) throws Exception {
		Optional<Book> getBookData = this.bookRepository.findById(bookId);
		if(getBookData.get().getId() != null) {
			getBookData.get().setBookName(book.getBookName());
			getBookData.get().setAuthorName(book.getAuthorName());
			getBookData.get().setLibray(book.getLibray());
			Book updatedBook = this.bookRepository.save(getBookData.get());
			return updatedBook;
		}
		else {
			throw new Exception("Book Not Found");
		}
	}
	
	public void deleteBookById(Long id) {
		this.bookRepository.deleteById(id);
	}
	
	
}
