package org.lessons.java.progetto_finale_backend.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.progetto_finale_backend.model.Videogame;
import org.lessons.java.progetto_finale_backend.service.GenreService;
import org.lessons.java.progetto_finale_backend.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String getListaPizzas(Model model) {
        List<Videogame> videogames = videogameService.findAll();

        model.addAttribute("videogames", videogames);

        return "videogames/index";
    }

    @GetMapping("/{id}")
    public String getPizzaById(@PathVariable("id") int id, Model model) {

        Optional<Videogame> game = videogameService.findById(id);
        if (game.isPresent()) {
            model.addAttribute("game", game.get());
            // model.addAttribute("ingredienti", ingredienteRepository.findAll());

            return "videogames/show";
        }
        return "redirect:videogames";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("videogame", new Videogame());
        model.addAttribute("genres", genreService.findAll());
        return "videogames/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            return "videogames/create-or-edit";
        }
        videogameService.save(formVideogame);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Videogame> game = videogameService.findById(id);
        if (game.isPresent()) {
            model.addAttribute("videogame", game.get());
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("edit", true);

            return "videogames/create-or-edit";
        }
        return "redirect:/videogames";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            return "videogames/create-or-edit";
        }
        videogameService.save(formVideogame);
        return "redirect:/videogames";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        videogameService.delete(id);
        return "redirect:/videogames";
    }

}
