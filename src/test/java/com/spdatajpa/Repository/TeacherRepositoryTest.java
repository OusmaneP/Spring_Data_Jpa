package com.spdatajpa.Repository;

import com.spdatajpa.Entity.Course;
import com.spdatajpa.Entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseDba = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(4)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Outub")
                .lastName("Khan")
                //.courses(List.of(courseDba, courseJava))
                .build();

        teacherRepository.save(teacher);
    }
}