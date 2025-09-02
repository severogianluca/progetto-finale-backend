package org.lessons.java.progetto_finale_backend.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.progetto_finale_backend.model.Genre;
import org.lessons.java.progetto_finale_backend.service.GenreService;
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
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String getGenresList(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "/genres/index";
    }

    @GetMapping("/{id}")
    public String getGenreById(@PathVariable("id") int id, Model model) {
        Optional<Genre> genre = genreService.findById(id);
        if (genre.isPresent()) {
            model.addAttribute("genre", genre.get());
            model.addAttribute("edit", true);

            return "genres/create-or-edit";
        }
        return "redirect:/genres";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/create-or-edit";
        }
        genreService.save(formGenre);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Genre> game = genreService.findById(id);
        if (game.isPresent()) {
            model.addAttribute("genre", game.get());
            model.addAttribute("edit", true);

            return "genres/create-or-edit";
        }
        return "redirect:/genres";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("genre") Genre formgenre, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/create-or-edit";
        }
        genreService.save(formgenre);
        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        genreService.delete(id);
        return "redirect:/genres";
    }

}
