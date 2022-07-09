package com.practice.springjpaadvanced.repository;

import com.practice.springjpaadvanced.entity.Course;
import com.practice.springjpaadvanced.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Repository
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {

        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }

        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);

        Course course2 = findById(10001L);

        course2.setName("JPA in 50 Steps - Updated");

    }

    public void addReviewForCourse() {
        // get the course
        Course course = findById(3L);
        logger.info("Course reviews -> {}", course.getReviews());

        //Add 2 reviews
        Review review1 = new Review("5", "Great hands-on stuff!");
        Review review2 = new Review("4", "Awesome");

        review1.setCourse(course);
        review2.setCourse(course);

        course.addReview(review1);
        course.addReview(review2);

        //save it to database
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewForCourse(Long courseId, List<Review> reviews) {
        // get the course
        Course course = findById(courseId);
        logger.info("Course reviews -> {}", course.getReviews());

        for (Review review : reviews) {
            //setting the relationship
            review.setCourse(course);
            course.addReview(review);
            em.persist(review);
        }
    }

}
