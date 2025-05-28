package com.project.Profile.and.Enrollment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.Profile.and.Enrollment.Dto.LoginStudentDto;
import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Repository.StudentRepository;


import org.springframework.http.HttpMethod;


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
		updstudent.setSemester(student.getSemester());
		updstudent.setYear(student.getYear());
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




	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public void syncStudentsFromLoginService() {
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8080/api/v1/auth/students/all";

		ResponseEntity<List<LoginStudentDto>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<LoginStudentDto>>() {}
		);
		System.out.println(response);

		List<LoginStudentDto> students = response.getBody();

		for (LoginStudentDto student : students) {
			System.out.println("Roll: " + student.getId() + ", Email: " + student.getEmail());
		}

		for (LoginStudentDto dto : students) {
			StudentEntity student = new StudentEntity();
			student.setRollNum(dto.getId());
			student.setName(dto.getName());
			student.setEmail(dto.getEmail());
			student.setProgram(dto.getDept()); // Assuming department is equivalent to program
			student.setYear(dto.getYear());

			studentRepository.save(student);
		}
	}


}