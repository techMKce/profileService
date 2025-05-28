package com.project.Profile.and.Enrollment.Dto;

import lombok.Data;

@Data
public class StudentCourseInfoDto {
    private String rollNum;
    private String name;
    private String department;

    public StudentCourseInfoDto(String rollNum, String program, String name) {
        this.rollNum = rollNum;
        this.department = program;
        this.name = name;
    }

}
