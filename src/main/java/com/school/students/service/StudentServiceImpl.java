package com.school.students.service;

import com.school.students.model.Student;
import com.school.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Wojciech Wasilewski
 * @date Created on 28.07.2019
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long studentId) {

        Optional<Student> result = studentRepository.findById(studentId);

        Student theStudent = null;
        if (result.isPresent()) {
            theStudent = result.get();
        } else {
            throw new RuntimeException("There is no student with id: " + studentId);
        }
        return theStudent;
    }

    @Override
    public List<Student> findStudentByLastname(String lastname) {
        return studentRepository.findByLastName(lastname);
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
