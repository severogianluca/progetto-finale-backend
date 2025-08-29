package org.lessons.java.progetto_finale_backend.controller;

import java.util.List;

import org.lessons.java.progetto_finale_backend.model.Videogame;
import org.lessons.java.progetto_finale_backend.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public String getListaPizzas(Model model) {
        List<Videogame> videogames = videogameService.findAll();

        model.addAttribute("videogames", videogames);

        return "videogames/index";
    }

}
