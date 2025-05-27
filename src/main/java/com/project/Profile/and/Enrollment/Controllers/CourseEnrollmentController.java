package com.project.Profile.and.Enrollment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> isStudentEnrolled(
	        @PathVariable String courseId,
	        @PathVariable String rollNum) {

	    boolean enrolled = service.isStudentEnrolledInCourse(courseId, rollNum);

	    if (enrolled) {
	        return ResponseEntity.ok("Student " + rollNum + " is enrolled in course " + courseId);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Student " + rollNum + " is NOT enrolled in course " + courseId);
	    }
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
