package com.project.Profile.and.Enrollment.Dto;

import lombok.Data;

@Data
public class LoginStudentDto {
    private String id;
    private String email;
    private String name;
    private String department;
    private String year;
}