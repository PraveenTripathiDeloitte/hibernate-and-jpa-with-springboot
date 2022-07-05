package com.practice.springjpaadvanced.repository;

import com.practice.springjpaadvanced.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    /**
     *     since fetch type is lazy,
     *     so we need this annotation so that before ending the session
     *     it performs the query to get passport details as well
     */
    public void testRetrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 2L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    public void testRetrievePassportAndAssociatedStudent() {
        Student student = em.find(Student.class, 2L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }


    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void saveStudentWithPassport() {
    }
}