package org.lessons.java.progetto_finale_backend.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.progetto_finale_backend.model.Videogame;
import org.lessons.java.progetto_finale_backend.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideogameService {
    
    @Autowired
    private VideogameRepository videogameRepository;

    public List<Videogame> findAll(){
        return videogameRepository.findAll();
    }

    public Optional<Videogame> findById(Integer id){
        return videogameRepository.findById(id);
    }

    public Videogame save(Videogame videogame){
        return videogameRepository.save(videogame);
    }

    public void delete(Integer id){
        videogameRepository.deleteById(id);
    }
}
