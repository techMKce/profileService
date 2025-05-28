package com.project.Profile.and.Enrollment.Controllers;

import java.util.List;

import com.project.Profile.and.Enrollment.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Profile.and.Enrollment.Entity.FacultyEntity;
import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Repository.FacultyRepository;
import com.project.Profile.and.Enrollment.Repository.StudentRepository;
import com.project.Profile.and.Enrollment.Service.AdminService;

@RestController
@RequestMapping("api/v1/profile/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

	@Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @GetMapping("/student")
    public List<StudentEntity> getAllStudent() {
        studentService.syncStudentsFromLoginService();
        return adminService.getAllStudent();
    }

    @GetMapping("/student/{rollnum}")
    public StudentEntity getStudentDetails(@PathVariable String rollnum) {
        return adminService.getStudentByRollNum(rollnum);
    }

    @PutMapping("/student/{rollnum}")
    public StudentEntity updateStudentDetails(@PathVariable String rollnum, @RequestBody StudentEntity student) {
        return adminService.updateStudent(rollnum, student);
    }
    
    @DeleteMapping("/student/{rollnum}")
    public StudentEntity deleteStudentDetails(@PathVariable String rollnum) {
        return adminService.deleteStudent(rollnum);
    }

    @GetMapping("/faculty")
    public List<FacultyEntity> getAllFaculty() {

        return adminService.getAllFaculty();
    }

	@GetMapping("/faculty/{staffId}")
	public FacultyEntity getFacultyDetails(@PathVariable String staffId) {
		return adminService.getFacultyByStaffId(staffId);
	}
	
	@PutMapping("/faculty/{staffId}")
	public FacultyEntity updateFacultyDetails(@PathVariable String staffId, @RequestBody FacultyEntity faculty) {
		return adminService.updateFaculty(staffId, faculty);
	}
	
	 @DeleteMapping("/faculty/{staffId}")
	    public FacultyEntity deleteFacultyDetails(@PathVariable String staffId) {
	        return adminService.deleteFaculty(staffId);
	    }

}
