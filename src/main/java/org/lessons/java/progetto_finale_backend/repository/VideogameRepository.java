package org.lessons.java.progetto_finale_backend.repository;

import org.lessons.java.progetto_finale_backend.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer>{
    
}
