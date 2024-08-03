package com.spdatajpa.Repository;

import com.spdatajpa.Entity.Course;
import com.spdatajpa.Entity.Student;
import com.spdatajpa.Entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Priyanka")
                .lastName("Singh")
                .build();

        Course course = Course
                .builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords)
                .getContent();

        Long totalElements = courseRepository.findAll(secondPageWithTwoRecords)
                        .getTotalElements();

        long totalPages = courseRepository.findAll(secondPageWithTwoRecords)
                        .getTotalPages();

        System.out.println("totalElements = " + totalElements);

        System.out.println("totalPages = " + totalPages);

        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllBySorting(){
        Pageable sortByTitle = PageRequest.of(
                0, 2, Sort.by("title")
        );

        Pageable sortByCreditDesc = PageRequest.of(
                0, 2, Sort.by("credit").descending()
        );

        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0, 2, Sort.by("title").descending().and(Sort.by("credit"))
        );

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageRecords = PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findByTitleContaining(
                "D", firstPageRecords)
                .getContent();

        System.out.println(courses);
    }

    @Test
    public void saveCourse(){
        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Haman")
                .lastName("Haaa")
                .emailId("Haaa@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }

}