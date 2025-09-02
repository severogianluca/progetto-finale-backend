package org.lessons.java.progetto_finale_backend.service;

import java.util.List;

import org.lessons.java.progetto_finale_backend.model.Genre;
import org.lessons.java.progetto_finale_backend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }
}
