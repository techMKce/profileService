package com.project.Profile.and.Enrollment.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="Student")
public class StudentEntity {

	@Id
	private String rollNum;
	
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
	private String fatherName;
	private String motherName;
	public enum fg{
		yes,
		no
	}
	private fg firstGraduate;
	private String institutionName;
	private String degree;
	private String program;
	private String startYear;
	private String gradutaionYear;
	private String cgpa;
	private String githubProfile;
	private String linkedInProfile;
	private String sslcSchoolName;
	private String sslcStartYear;
	private String sslcEndYear;
	private String sslcPercentage;
	public enum sslc{
		cbse,
		state,
		icse
	}
	private sslc sslcboardOfEducation;
	private String hscSchoolName;
	private String hscStartYear;
	private String hscEndYear;
	private String hscPercentage;
	public enum hsc{
		cbse,
		state,
		icse	
	}
	private String hscboardOfEducation;
	public String getRollNum() {
		return rollNum;
	}
	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
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
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public fg getFirstGraduate() {
		return firstGraduate;
	}
	public void setFirstGraduate(fg firstGraduate) {
		this.firstGraduate = firstGraduate;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getGradutaionYear() {
		return gradutaionYear;
	}
	public void setGradutaionYear(String gradutaionYear) {
		this.gradutaionYear = gradutaionYear;
	}
	public String getCgpa() {
		return cgpa;
	}
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
	public String getGithubProfile() {
		return githubProfile;
	}
	public void setGithubProfile(String githubProfile) {
		this.githubProfile = githubProfile;
	}
	public String getLinkedInProfile() {
		return linkedInProfile;
	}
	public void setLinkedInProfile(String linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}
	public String getSslcSchoolName() {
		return sslcSchoolName;
	}
	public void setSslcSchoolName(String sslcSchoolName) {
		this.sslcSchoolName = sslcSchoolName;
	}
	public String getSslcStartYear() {
		return sslcStartYear;
	}
	public void setSslcStartYear(String sslcStartYear) {
		this.sslcStartYear = sslcStartYear;
	}
	public String getSslcEndYear() {
		return sslcEndYear;
	}
	public void setSslcEndYear(String sslcEndYear) {
		this.sslcEndYear = sslcEndYear;
	}
	public String getSslcPercentage() {
		return sslcPercentage;
	}
	public void setSslcPercentage(String sslcPercentage) {
		this.sslcPercentage = sslcPercentage;
	}
	public sslc getSslcboardOfEducation() {
		return sslcboardOfEducation;
	}
	public void setSslcboardOfEducation(sslc sslcboardOfEducation) {
		this.sslcboardOfEducation = sslcboardOfEducation;
	}
	public String getHscSchoolName() {
		return hscSchoolName;
	}
	public void setHscSchoolName(String hscSchoolName) {
		this.hscSchoolName = hscSchoolName;
	}
	public String getHscStartYear() {
		return hscStartYear;
	}
	public void setHscStartYear(String hscStartYear) {
		this.hscStartYear = hscStartYear;
	}
	public String getHscEndYear() {
		return hscEndYear;
	}
	public void setHscEndYear(String hscEndYear) {
		this.hscEndYear = hscEndYear;
	}
	public String getHscPercentage() {
		return hscPercentage;
	}
	public void setHscPercentage(String hscPercentage) {
		this.hscPercentage = hscPercentage;
	}
	public String getHscboardOfEducation() {
		return hscboardOfEducation;
	}
	public void setHscboardOfEducation(String hscboardOfEducation) {
		this.hscboardOfEducation = hscboardOfEducation;
	}
	public StudentEntity(String rollNum, String image, String name, String email, gen gender, LocalDate dob,
			String phoneNum, String bloodGroup, String nationality, String address, String adharNum, String fatherName,
			String motherName, fg firstGraduate, String institutionName, String degree, String program,
			String startYear, String gradutaionYear, String cgpa, String githubProfile, String linkedInProfile,
			String sslcSchoolName, String sslcStartYear, String sslcEndYear, String sslcPercentage,
			sslc sslcboardOfEducation, String hscSchoolName, String hscStartYear, String hscEndYear,
			String hscPercentage, String hscboardOfEducation) {
		super();
		this.rollNum = rollNum;
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
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.firstGraduate = firstGraduate;
		this.institutionName = institutionName;
		this.degree = degree;
		this.program = program;
		this.startYear = startYear;
		this.gradutaionYear = gradutaionYear;
		this.cgpa = cgpa;
		this.githubProfile = githubProfile;
		this.linkedInProfile = linkedInProfile;
		this.sslcSchoolName = sslcSchoolName;
		this.sslcStartYear = sslcStartYear;
		this.sslcEndYear = sslcEndYear;
		this.sslcPercentage = sslcPercentage;
		this.sslcboardOfEducation = sslcboardOfEducation;
		this.hscSchoolName = hscSchoolName;
		this.hscStartYear = hscStartYear;
		this.hscEndYear = hscEndYear;
		this.hscPercentage = hscPercentage;
		this.hscboardOfEducation = hscboardOfEducation;
	}
	public StudentEntity() {
		super();
	}
	
	
}
