package com.spdatajpa.Repository;

import com.spdatajpa.Entity.Guardian;
import com.spdatajpa.Entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
//                .guardianName("Nikhil")
//                .guardianEmail("nikhil@gmail.com")
//                .guardianMobile("99999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("99999999")
                .build();

        Student student = Student.builder()
                .firstName("Shivan")
                .lastName("Kumar")
                .emailId("shivam@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Shivan");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContainingOrLastNameContaining("bir", "mar");
        System.out.println(students);
    }

    @Test
    public void printStudentGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Nikhil");
        System.out.println(students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("shivam@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String name = studentRepository.getStudentFirstNameByEmailAddress("shivam@gmail.com");
        System.out.println("student name = " + name);
    }

    @Test
    public void printgetStudentByEmailAddNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("shivam@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printgetStudentByEmailAddNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("shabbir@gmail.com");
        System.out.println(student);
    }






}