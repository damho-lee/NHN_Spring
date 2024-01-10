package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.StudentRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final StudentRepository studentRepository;

    public LoginController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String getLoginForm(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loginId") != null) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping
    public String loginCheck(@RequestParam("id") long id,
                             @RequestParam("name") String name,
                             HttpServletRequest request) {
        System.out.println("id : " + id + ", name: " + name);

        if (studentRepository.matches(id, name)) {
            request.getSession(true).setAttribute("loginId", id);
            return "redirect:/";
        }

        return "redirect:/login";
    }
}
