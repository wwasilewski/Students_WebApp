package com.school.students.repository;

import com.school.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wojciech Wasilewski
 * @date Created on 28.07.2019
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String lastname);
}
