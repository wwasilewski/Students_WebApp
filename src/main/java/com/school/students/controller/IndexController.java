package com.school.students.controller;

import com.school.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Wojciech Wasilewski
 * @date Created on 28.07.2019
 */
@Controller
public class IndexController {

    private StudentService studentService;

    @Autowired
    public IndexController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"/", "/index", ""})
    public String showIndex(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        return "index";
    }
}
