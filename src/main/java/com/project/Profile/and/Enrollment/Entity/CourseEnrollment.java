package com.project.Profile.and.Enrollment.Entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CourseEnrollment")
public class CourseEnrollment {

	@Id
    private String courseId;

    @ElementCollection
    private List<String> rollNums;

    public CourseEnrollment() {}

    public CourseEnrollment(String courseId, List<String> rollNums) {
        this.courseId = courseId;
        this.rollNums = rollNums;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<String> getRollNums() {
        return rollNums;
    }

    public void setRollNums(List<String> rollNums) {
        this.rollNums = rollNums;
    }
}
