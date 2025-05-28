package com.project.Profile.and.Enrollment.Service;

import java.util.List;

import com.project.Profile.and.Enrollment.Dto.LoginFacultyDto;
import com.project.Profile.and.Enrollment.Dto.LoginStudentDto;
import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Profile.and.Enrollment.Dto.FacultyDto;
import com.project.Profile.and.Enrollment.Entity.FacultyEntity;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Repository.FacultyRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class FacultyService {
	 
	@Autowired
    private FacultyRepository facultyRepository;

    public FacultyEntity createFaculty(FacultyEntity faculty) {
        return facultyRepository.save(faculty);
    }

    public FacultyEntity getFacultyByStaffId(String staffId) {
        return facultyRepository.findByStaffId(staffId)
        		.orElseThrow(() -> new ResourceNotFoundException("Faculty not found with ID: " + staffId));
    }

    public FacultyEntity updateFaculty(String staffId, FacultyEntity faculty) {
    	FacultyEntity updfaculty = facultyRepository.findByStaffId(staffId)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with ID: " + staffId));

        updfaculty.setImage(faculty.getImage());
        updfaculty.setDob(faculty.getDob());
        updfaculty.setGender(faculty.getGender());
        updfaculty.setPhoneNum(faculty.getPhoneNum());
        updfaculty.setAddress(faculty.getAddress());
        updfaculty.setAdharNum(faculty.getAdharNum());
        updfaculty.setInstitutionName(faculty.getInstitutionName());
        updfaculty.setStartYear(faculty.getStartYear());
        updfaculty.setDepartment(faculty.getDepartment());
        updfaculty.setExperience(faculty.getExperience());
        updfaculty.setDesignation(faculty.getDesignation());
        updfaculty.setDescription(faculty.getDescription());
        updfaculty.setEndYear(faculty.getEndYear());
        updfaculty.setBloodGroup(faculty.getBloodGroup());
        updfaculty.setNationality(faculty.getNationality());
        updfaculty.setAchievements(faculty.getAchievements());
        updfaculty.setResearchDetails(faculty.getResearchDetails());

        return facultyRepository.save(updfaculty);
    }
    public List<FacultyEntity> getAllFaculty() {
        return facultyRepository.findAll(); 
    }
    public List<FacultyDto> getFacultyNamesByDepartment(String department) {
        List<FacultyEntity> facultyList = facultyRepository.findByDepartmentIgnoreCase(department);

        if (facultyList.isEmpty()) {
            throw new ResourceNotFoundException("No faculty found in department: " + department);
        }

        return facultyList.stream()
                .map(f -> new FacultyDto(f.getStaffId(), f.getName()))
                .toList();
    }

    public void syncFacultyFromLoginService() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/v1/auth/faculty/all";

        ResponseEntity<List<LoginFacultyDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LoginFacultyDto>>() {}
        );
        System.out.println(response);

        List<LoginFacultyDto> faculty = response.getBody();

        for (LoginFacultyDto student : faculty) {
            System.out.println("Roll: " + student.getId() + ", Email: " + student.getEmail());
        }

        for (LoginFacultyDto dto : faculty) {
            FacultyEntity student = new FacultyEntity();
            student.setStaffId(dto.getId());
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setDepartment(dto.getDept()); // Assuming department is equivalent to progra

            facultyRepository.save(student);
        }
    }

}
