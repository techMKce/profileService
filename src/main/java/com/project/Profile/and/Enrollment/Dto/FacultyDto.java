package com.project.Profile.and.Enrollment.Dto;

public class FacultyDto {

	 private String staffId;
	    private String name;

	    public FacultyDto(String staffId, String name) {
	        this.staffId = staffId;
	        this.name = name;
	    }

	    public String getStaffId() {
	        return staffId;
	    }

	    public String getName() {
	        return name;
	    }
}
