package com.project.Profile.and.Enrollment.Dto;

import java.util.List;

public class FacultyStudentAssignmentDto {
    private String facultyId;
    private String courseId;
    private List<String> rollNums;

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

    public List<String> getRollNums() {
        return rollNums;
    }

    public void setRollNums(List<String> rollNums) {
        this.rollNums = rollNums;
    }
}
