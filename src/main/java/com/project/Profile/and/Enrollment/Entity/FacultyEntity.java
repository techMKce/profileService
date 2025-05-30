package com.project.Profile.and.Enrollment.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="Faculty")
public class FacultyEntity {

	@Id
	private String staffId;
	
	private String image;
	private String name;
	private String email;
	public enum gen{
		male,
		female,
		others
	}
	private gen gender;
	private LocalDate dob;
	private String phoneNum;
	private String bloodGroup;
	private String nationality;
	private String address;
	private String adharNum;
	private String department;
	private int experience;
	private String designation;
	private String institutionName;
	private String startYear;
	private String endYear;
	private String description;
	private String achievements;
	private String researchDetails;
	public String getStaffId() {
	    return staffId;
	}

	public void setStaffId(String staffId) {
	    this.staffId = staffId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public gen getGender() {
		return gender;
	}
	public void setGender(gen gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAdharNum() {
		return adharNum;
	}
	public void setAdharNum(String adharNum) {
		this.adharNum = adharNum;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAchievements() {
		return achievements;
	}
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	public String getResearchDetails() {
		return researchDetails;
	}
	public void setResearchDetails(String researchDetails) {
		this.researchDetails = researchDetails;
	}
	public FacultyEntity(String staffid, String image, String name, String email, gen gender, LocalDate dob,
			String phoneNum, String bloodGroup, String nationality, String address, String adharNum, String department,
			int experience, String designation, String institutionName, String startYear, String endYear,
			String description, String achievements, String researchDetails) {
		super();
		this.staffId = staffid;
		this.image = image;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.phoneNum = phoneNum;
		this.bloodGroup = bloodGroup;
		this.nationality = nationality;
		this.address = address;
		this.adharNum = adharNum;
		this.department = department;
		this.experience = experience;
		this.designation = designation;
		this.institutionName = institutionName;
		this.startYear = startYear;
		this.endYear = endYear;
		this.description = description;
		this.achievements = achievements;
		this.researchDetails = researchDetails;
	}
	public FacultyEntity() {
		super();
	}

}
