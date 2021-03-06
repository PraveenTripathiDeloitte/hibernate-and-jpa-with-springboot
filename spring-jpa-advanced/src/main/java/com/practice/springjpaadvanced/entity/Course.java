package com.practice.springjpaadvanced.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /* one course can have multiple review we want course_id in review table only
       So winning side is course that's why we're using mapped By here
     */
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    /* one course can be taken by many students
       we have added mappedBy here because we wanted
       to make student as owner of the relationship
       so this will not generate table courses_student
     */
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    protected Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    /*
     we don't want someone to add list of reviews that's why we've not created setter for list of reviews.
     We only created method to add a single review. We want to add one review at a time
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    /* we don't want someone to add list of students to course that's why
    we haven't created setter for adding list of students we just created
    one method to add a single student to the course
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
