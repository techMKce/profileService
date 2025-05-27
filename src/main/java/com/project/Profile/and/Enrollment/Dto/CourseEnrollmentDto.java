package com.project.Profile.and.Enrollment.Dto;

public class CourseEnrollmentDto {

	private String courseId;
    private String rollNum;
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getRollNum() {
		return rollNum;
	}
	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
	}
	public CourseEnrollmentDto(String courseId, String rollNum) {
		super();
		this.courseId = courseId;
		this.rollNum = rollNum;
	}
	public CourseEnrollmentDto() {
		super();
	}
    
    

}
