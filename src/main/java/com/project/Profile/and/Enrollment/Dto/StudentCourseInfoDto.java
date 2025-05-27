package com.project.Profile.and.Enrollment.Dto;

public class StudentCourseInfoDto {
    private String rollNum;
    private String department;

    public StudentCourseInfoDto() {}

    public StudentCourseInfoDto(String rollNum, String department) {
        this.rollNum = rollNum;
        this.department = department;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
