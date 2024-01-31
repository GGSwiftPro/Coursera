package org.example.Model;

import java.time.LocalDate;

public class StudentCourses {
    String student_pin;
    int course_id;
    LocalDate completion_date;

    public String getStudent_pin() {
        return student_pin;
    }

    public void setStudent_pin(String student_pin) {
        this.student_pin = student_pin;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public LocalDate getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(LocalDate completion_date) {
        this.completion_date = completion_date;
    }

    public StudentCourses(String student_pin, int course_id, LocalDate completion_date) {
        this.student_pin = student_pin;
        this.course_id = course_id;
        this.completion_date = completion_date;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "student_pin='" + student_pin + '\'' +
                ", course_id=" + course_id +
                ", completion_date=" + completion_date +
                '}';
    }
}
