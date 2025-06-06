package com.project.Profile.and.Enrollment.Controllers;

import java.util.List;

import com.project.Profile.and.Enrollment.Dto.StudentCourseInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.project.Profile.and.Enrollment.Dto.CourseEnrollmentDto;
import com.project.Profile.and.Enrollment.Entity.CourseEnrollment;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Service.CourseEnrollmentService;

@RestController
@RequestMapping("/api/v1/course-enrollment")
public class CourseEnrollmentController {

	@Autowired
    private CourseEnrollmentService service;

	@PostMapping
	public CourseEnrollment enroll(@RequestBody CourseEnrollmentDto request) {
	    return service.enrollStudent(request.getCourseId(), request.getRollNum());
	}

	@GetMapping
    public List<CourseEnrollment> getAllEnrollments() {
    	return service.getAllEnrollments();
    }
	
	@GetMapping("/by-course/{courseId}")
	public CourseEnrollment getEnrollmentByCourseId(@PathVariable String courseId) {
	    return service.getEnrollmentByCourseId(courseId)
	            .orElseThrow(() -> new ResourceNotFoundException("No courses found with the course ID: " + courseId));
	}

	@GetMapping("/by-student/{rollNum}")
	public List<String> getCourseIdsByStudent(@PathVariable String rollNum) {
	    return service.getCourseIdsByRollNum(rollNum);
	}

	@GetMapping("/check/{courseId}/{rollNum}")
	public ResponseEntity<Boolean> isStudentEnrolled(
	        @PathVariable String courseId,
	        @PathVariable String rollNum) {

	    return ResponseEntity.ok(service.isStudentEnrolledInCourse(courseId, rollNum));
	}

	@GetMapping("/student-details/{courseId}")
	public List<StudentCourseInfoDto> getStudentDetails(@PathVariable String courseId) {
		return service.getEnrolledStudentsWithDepartmentandName(courseId);
	}

	@GetMapping("/students-by-department/{courseId}/{dept}")
	public List<StudentCourseInfoDto> getStudentsByCourseAndDepartment(
			@PathVariable String courseId,
			@PathVariable String dept) {
		return service.getEnrolledStudentsByCourseAndDept(courseId, dept);
	}

	@DeleteMapping("/delete-course/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable String courseId) {
		service.deleteCourseAndEnrollments(courseId);
		return ResponseEntity.ok("Course and its enrollments deleted successfully.");
	}

	// total number of enrollment
	
	        @GetMapping("/total-enrolled-count")
	        public int getTotalEnrolledStudentCount() {
	            return service.getTotalEnrolledStudentCount();
	        }
	        
	        //total number of course enrolled by a particular student
	        
	        @GetMapping("/count-by-student/{rollNum}")
	        public int getTotalCoursesByStudent(@PathVariable String rollNum) {
	            return service.getTotalCoursesEnrolledByStudent(rollNum);
	        }
	    
}
