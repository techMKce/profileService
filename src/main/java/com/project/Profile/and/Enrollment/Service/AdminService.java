package com.project.Profile.and.Enrollment.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Profile.and.Enrollment.Entity.FacultyEntity;
import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Repository.FacultyRepository;
import com.project.Profile.and.Enrollment.Repository.StudentRepository;

@Service
public class AdminService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;

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
    
    public StudentEntity deleteStudent(String rollnum) {
    	Optional<StudentEntity> studentOpt = studentRepository.findById(rollnum);
        if (studentOpt.isPresent()) {
            StudentEntity student = studentOpt.get();
            studentRepository.deleteById(rollnum);
            return student;
        } else {
            throw new RuntimeException("Student with roll number " + rollnum + " not found");
        }
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
    
    public FacultyEntity deleteFaculty(String staffId) {
    	Optional<FacultyEntity> facultyOpt = facultyRepository.findById(staffId);
        if (facultyOpt.isPresent()) {
            FacultyEntity faculty = facultyOpt.get();
            facultyRepository.deleteById(staffId);
            return faculty;
        } else {
            throw new RuntimeException("Facultyt with staff id " + staffId + " not found");
        }
    }
    
    
}
