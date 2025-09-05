package org.lessons.java.progetto_finale_backend.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.lessons.java.progetto_finale_backend.model.Videogame;
import org.lessons.java.progetto_finale_backend.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/videogames")
public class VideogameRestController {

    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getList() {
        Map<String, Object> response = new HashMap<>();
        response.put("Messaggio", "Ecco la lista dei videogame");
        response.put("data", videogameService.findAll());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Videogame> videogame = videogameService.findById(id);

        if (videogame.isEmpty()) {
            response.put("Messaggio", "Attenzione elemento non trovato");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("Messaggio", "Ecco il videogame selezionato");
        response.put("data", videogame.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> store(@Valid @RequestBody Videogame videogame) {
        Map<String, Object> response = new HashMap<>();
        Videogame game = videogameService.save(videogame);

        response.put("Messaggio", "Bene inserito con successo!");
        response.put("data", game);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Videogame videogame, @PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Videogame> game = videogameService.findById(id);

        if (game.isEmpty()) {
            response.put("Messaggio", "Attenzione errore durante la modifica del videogame ID non trovato!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        videogame.setId(id);
        Videogame videogameModificato = videogameService.save(videogame);

        response.put("Messaggio", "Bene modificato con successo!");
        response.put("data", videogameModificato);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        if (videogameService.findById(id).isEmpty()) {
            response.put("Messaggio", "Attenzione errore durante la cancellazione del videogame ID non trovato!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("Messaggio", "Bene cancellato con successo!");
        videogameService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
