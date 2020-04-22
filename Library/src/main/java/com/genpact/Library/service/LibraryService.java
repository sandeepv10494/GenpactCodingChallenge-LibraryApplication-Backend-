package com.genpact.Library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.Library.model.Library;
import com.genpact.Library.repository.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;
	
	public List<Library> getAllLibraries(){
		return this.libraryRepository.findAll();
	}
	
	public Optional<Library> getLibrary(Long id) {
		return this.libraryRepository.findById(id);
	}
	
	public Optional<Library> getLibraryByName(String name){
		return this.libraryRepository.findByName(name);
	}
	
	public Library saveLibrary(Library library) {
		Library newLibrary = this.libraryRepository.save(library);
		return newLibrary;
	}
}
