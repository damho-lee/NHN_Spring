package com.nhnacademy.springmvc.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewController {

    //RequestHandler
    @GetMapping("/now/{nick}")
    public String now(
            @RequestParam String name,
            @PathVariable(name = "nick") String nick,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("nick", nick);
        model.addAttribute("time", new Date().toString());


        return "now";
    }
}
