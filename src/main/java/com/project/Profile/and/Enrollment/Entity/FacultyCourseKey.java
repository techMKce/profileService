package com.project.Profile.and.Enrollment.Entity;

import java.io.Serializable;

import java.util.Objects;
import jakarta.persistence.Embeddable;

public class FacultyCourseKey implements Serializable {

    private String facultyId;
    private String courseId;

    public FacultyCourseKey() {
    }

    public FacultyCourseKey(String facultyId, String courseId) {
        this.facultyId = facultyId;
        this.courseId = courseId;
    }

    // Getters and Setters
    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyCourseKey)) return false;
        FacultyCourseKey that = (FacultyCourseKey) o;
        return Objects.equals(facultyId, that.facultyId) &&
               Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, courseId);
    }
}