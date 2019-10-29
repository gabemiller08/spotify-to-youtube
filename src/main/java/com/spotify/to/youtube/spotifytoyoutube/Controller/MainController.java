package com.spotify.to.youtube.spotifytoyoutube.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "/")
    public String homePage(Model model){
        model.addAttribute("objectKey", new Object());
        return "home";
    }
}
