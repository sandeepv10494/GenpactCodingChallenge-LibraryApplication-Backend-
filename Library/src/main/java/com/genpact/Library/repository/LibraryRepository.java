package com.genpact.Library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genpact.Library.model.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

	Optional<Library> findByName(String name);
	
}
