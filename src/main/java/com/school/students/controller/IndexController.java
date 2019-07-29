package com.school.students.controller;

import com.school.students.model.Student;
import com.school.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/";
    }
}
