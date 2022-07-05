package com.practice.springjpaadvanced.repository;

import com.practice.springjpaadvanced.entity.Passport;
import com.practice.springjpaadvanced.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional // required if we wanted to make any changes in the database
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student) {

        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }

        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        /*
        this is required otherwise it will throw error that
        TransientPropertyValueException
        To remove this first we need to save the passport in databse
        then only we can create relationship
         */
        em.persist(passport);
        Student student1 = new Student("Harry");
        student1.setPassport(passport);
        em.persist(student1);
    }

}
