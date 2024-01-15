package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRequest;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentRestController {
    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public void createStudent(@RequestBody StudentRequest studentRequest,
                                Model model) {
        System.out.println(studentRequest);
        Student student = studentRepository.register(studentRequest.getName(), studentRequest.getEmail(), studentRequest.getScore(),
                studentRequest.getComment());
        model.addAttribute("student", student);
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable long studentId) {
        return studentRepository.getStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public void modifyStudent(@RequestBody StudentRequest studentRequest,
                              @PathVariable long studentId) {
        Student student = studentRepository.getStudent(studentId);

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setScore(studentRequest.getScore());
        student.setComment(studentRequest.getComment());
    }
}
