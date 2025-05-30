package com.project.Profile.and.Enrollment.Dto;

import lombok.Data;

@Data
public class StudentCourseInfoDto {
    private String rollNum;
    private String name;
    private String department;
    private int semester;

    public StudentCourseInfoDto(String rollNum, String program, String name ,int semester) {
        this.rollNum = rollNum;
        this.department = program;
        this.name = name;
        this.semester=semester;
    }

}
