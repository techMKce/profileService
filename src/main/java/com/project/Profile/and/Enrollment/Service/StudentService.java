package com.project.Profile.and.Enrollment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Repository.StudentRepository;

@Service
public class StudentService {


	    @Autowired
	    private StudentRepository studentRepository;

	    public StudentEntity createStudent(StudentEntity student) {
	        return studentRepository.save(student);
	    }

	    public StudentEntity getStudentByRollNum(String rollNum) {
	        return studentRepository.findByRollNum(rollNum)
	        		.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + rollNum));
	    }

	    public StudentEntity updateStudent(String rollNum, StudentEntity student) {
	        StudentEntity updstudent = studentRepository.findByRollNum(rollNum)
	        		.orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + rollNum));

	        updstudent.setImage(student.getImage());
	        updstudent.setDob(student.getDob());
	        updstudent.setGender(student.getGender());
	        updstudent.setPhoneNum(student.getPhoneNum());
	        updstudent.setBloodGroup(student.getBloodGroup());
	        updstudent.setNationality(student.getNationality());
	        updstudent.setAddress(student.getAddress());
	        updstudent.setAdharNum(student.getAdharNum());
	        updstudent.setInstitutionName(student.getInstitutionName());
	        updstudent.setDegree(student.getDegree());
	        updstudent.setProgram(student.getProgram());
	        updstudent.setCgpa(student.getCgpa());
	        updstudent.setStartYear(student.getStartYear());
	        updstudent.setGradutaionYear(student.getGradutaionYear());
	        updstudent.setFirstGraduate(student.getFirstGraduate());
	        updstudent.setLinkedInProfile(student.getLinkedInProfile());
	        updstudent.setGithubProfile(student.getGithubProfile());
	        updstudent.setFatherName(student.getFatherName());
	        updstudent.setMotherName(student.getMotherName());
	        updstudent.setHscSchoolName(student.getHscSchoolName());
	        updstudent.setHscStartYear(student.getHscStartYear());
	        updstudent.setHscEndYear(student.getHscEndYear());
	        updstudent.setHscPercentage(student.getHscPercentage());
	        updstudent.setHscboardOfEducation(student.getHscboardOfEducation());
	        updstudent.setSslcSchoolName(student.getSslcSchoolName());
	        updstudent.setSslcStartYear(student.getSslcStartYear());
	        updstudent.setSslcEndYear(student.getSslcEndYear());
	        updstudent.setSslcPercentage(student.getSslcPercentage());
	        updstudent.setSslcboardOfEducation(student.getSslcboardOfEducation());

	        return studentRepository.save(updstudent);
	    }

	    public List<StudentEntity> getAllStudent() {
	        return studentRepository.findAll();
	    }
	    }
