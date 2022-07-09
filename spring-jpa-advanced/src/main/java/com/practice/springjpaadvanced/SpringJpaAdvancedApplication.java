package com.practice.springjpaadvanced;

import com.practice.springjpaadvanced.entity.Course;
import com.practice.springjpaadvanced.entity.Review;
import com.practice.springjpaadvanced.repository.CourseRepository;
import com.practice.springjpaadvanced.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJpaAdvancedApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaAdvancedApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.saveStudentWithPassport();
        logger.info("Student saved!!!");
        courseRepository.save(new Course("Wizardy"));
        logger.info("Course saved");
//        courseRepository.addReviewForCourse();
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("5", "Great hands-on !"));
        reviews.add(new Review("4", "Awesome learning"));
        courseRepository.addReviewForCourse(3L, reviews);
    }
}
