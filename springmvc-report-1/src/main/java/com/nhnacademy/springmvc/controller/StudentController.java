package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentHideScoreRequest;
import com.nhnacademy.springmvc.domain.StudentRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") long studentId) {
        Student student = studentRepository.getStudent(studentId);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }

        return student;
    }

    @GetMapping("/{studentId}")
    public String getStudentInfo(@ModelAttribute Student student,
                                 Model model) {
        model.addAttribute("student", student);

        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@ModelAttribute Student student,
                                    ModelMap modelMap) {
        modelMap.addAttribute("student", student);

        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyStudent(@Valid @ModelAttribute StudentRequest studentRequest,
                                @PathVariable("studentId") long studentId,
                                BindingResult bindingResult,
                                ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.getStudent(studentId);

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setScore(studentRequest.getScore());
        student.setComment(studentRequest.getComment());

        modelAndView.addObject(student);

        return "studentView";
    }

    @GetMapping(value = "/{studentId}", params = {"hideScore=yes"})
    public String getStudentInfoHideScores(@ModelAttribute Student student,
                                           Model model) {
        StudentHideScoreRequest studentHideScoreRequest =
                new StudentHideScoreRequest(student.getId(), student.getName(), student.getEmail(), "#####", "#####");

        model.addAttribute("student", studentHideScoreRequest);

        return "studentView";
    }

    @ExceptionHandler({StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(StudentNotFoundException exception, Model model) {
        model.addAttribute("exception", exception);

        return "error";
    }

}