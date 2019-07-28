package com.school.students.service;

import com.school.students.model.Student;

import java.util.List;

/**
 * @author Wojciech Wasilewski
 * @date Created on 28.07.2019
 */
public interface StudentService {

    // method to find all students
    List<Student> findAllStudents();

    // method to find student by id
    Student findStudentById(Long studentId);

    // method to find student by lastname
    List<Student> findStudentByLastname(String lastname);

    // method to add a student
    void addStudent(Student student);

    // method to delete a student by id
    void deleteStudentById(Long studentId);
}
