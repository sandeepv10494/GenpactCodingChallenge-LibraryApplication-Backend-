package com.genpact.Library.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.Library.model.Book;
import com.genpact.Library.model.Library;
import com.genpact.Library.service.BookService;
import com.genpact.Library.service.LibraryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MainController {

	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/libraries")
	public ResponseEntity<List<Library>> getAllLibraries(){
		return ResponseEntity.ok().body(this.libraryService.getAllLibraries());
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		return ResponseEntity.ok().body(this.bookService.getAllBooks());
	}
	
	@GetMapping("/library/{libraryName}/books")
	public List<Book> getBookForLibrary(@PathVariable ("libraryName") String libraryName){
		return this.bookService.getBooksByLibraryName(libraryName);
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> saveBook(@RequestBody Book book){
		Book savedBook = this.bookService.saveBookData(book);
		return ResponseEntity.ok().body(savedBook);
	}
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) throws Exception{
		Book updatedBook = this.bookService.updateBook(bookId,book);
		return ResponseEntity.ok().body(updatedBook);
	}
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<String> deleteBookRecord(@PathVariable("bookId") Long bookId){
		this.bookService.deleteBookById(bookId);
		return ResponseEntity.ok().body("Book deleted successfully");
	}
	
}
