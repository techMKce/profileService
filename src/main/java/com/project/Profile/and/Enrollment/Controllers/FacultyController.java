package com.project.Profile.and.Enrollment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.Profile.and.Enrollment.Dto.FacultyDto;
import com.project.Profile.and.Enrollment.Entity.FacultyEntity;
import com.project.Profile.and.Enrollment.Service.FacultyService;

@RestController
@RequestMapping("api/v1/profile/faculty")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@PostMapping
	public FacultyEntity createFacultyDetails(@RequestBody FacultyEntity faculty) {
		return facultyService.createFaculty(faculty);
	}
	
	 @GetMapping
	    public List<FacultyEntity> getAllFaculty() {
	        return facultyService.getAllFaculty();
	    }

	@GetMapping("/{staffId}")
	public FacultyEntity getFacultyDetails(@PathVariable String staffId) {
		return facultyService.getFacultyByStaffId(staffId);
	}

	@PutMapping("/{staffId}")
	public FacultyEntity updateFacultyDetails(@PathVariable String staffId, @RequestBody FacultyEntity faculty) {
		return facultyService.updateFaculty(staffId, faculty);
	}
	
	@GetMapping("/by-department/{department}")
	public List<FacultyDto> getFacultyNamesByDepartment(@PathVariable String department) {
	    return facultyService.getFacultyNamesByDepartment(department);
	}
}
