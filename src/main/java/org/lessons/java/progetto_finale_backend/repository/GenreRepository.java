package org.lessons.java.progetto_finale_backend.repository;

import org.lessons.java.progetto_finale_backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

} 