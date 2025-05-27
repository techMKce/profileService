package com.project.Profile.and.Enrollment.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Profile.and.Enrollment.Entity.CourseEnrollment;
import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Repository.CourseEnrollmentRepository;
import com.project.Profile.and.Enrollment.Repository.StudentRepository;

@Service
public class CourseEnrollmentService {

	 @Autowired
	 private CourseEnrollmentRepository repository;
	 
	 @Autowired
	 private StudentRepository studentRepository;

	@Autowired
	private MailService mailService;

	    public CourseEnrollment enrollStudent(String courseId, String rollNum) {
	        Optional<CourseEnrollment> existing = repository.findById(courseId);

			StudentEntity student = studentRepository.findByRollNum(rollNum)
					.orElseThrow(() -> new ResourceNotFoundException("Student not found with roll number: " + rollNum));


	        if (existing.isPresent()) {
	            CourseEnrollment course = existing.get();
	            List<String> rollNums = course.getRollNums();

	            if (!rollNums.contains(rollNum)) {
	                rollNums.add(rollNum);
	                course.setRollNums(rollNums);
					mailService.sendEnrollmentMail(student.getEmail(), courseId);
	                return repository.save(course);
	            }

	            return course;
	        } else {
	            List<String> rolls = new ArrayList<>();
	            rolls.add(rollNum);
	            CourseEnrollment newCourse = new CourseEnrollment(courseId, rolls);
				mailService.sendEnrollmentMail(student.getEmail(), courseId);
	            return repository.save(newCourse);
	        }
	    }

	    public List<CourseEnrollment> getAllEnrollments() {
	    	List<CourseEnrollment> enrollments = repository.findAll();
	        if (enrollments.isEmpty()) {
	            throw new ResourceNotFoundException("No enrollments found");
	        }
	        return enrollments;
	    }
	    
	    public Optional<CourseEnrollment> getEnrollmentByCourseId(String courseId) {
	        return repository.findById(courseId);
	    }
	    
	    public List<String> getCourseIdsByRollNum(String rollNum) {
	    	List<String> courseIds = repository.findAll().stream()
	    	        .filter(course -> course.getRollNums().contains(rollNum))
	    	        .map(CourseEnrollment::getCourseId)
	    	        .toList();

	    	    if (courseIds.isEmpty()) {
	    	        throw new ResourceNotFoundException("No courses found for roll number: " + rollNum);
	    	    }

	    	    return courseIds;
	    }
	    
	    public boolean isStudentEnrolledInCourse(String courseId, String rollNum) {
	        CourseEnrollment course = repository.findById(courseId)
	            .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

	        return course.getRollNums().contains(rollNum);
	    }
	    //get total enrollement count 
	    public int getTotalEnrolledStudentCount() {
	        List<CourseEnrollment> enrollments = repository.findAll();

	        int totalCount = 0;
	        for (CourseEnrollment enrollment : enrollments) {
	            totalCount += enrollment.getRollNums().size();
	        }

	        return totalCount;
	    }
	    
	 // Returns how many courses a student is enrolled in
	    public int getTotalCoursesEnrolledByStudent(String rollNum) {
	        List<CourseEnrollment> enrollments = repository.findAll();

	        int count = 0;
	        for (CourseEnrollment enrollment : enrollments) {
	            if (enrollment.getRollNums().contains(rollNum)) {
	                count++;
	            }
	        }

	        if (count == 0) {
	            throw new ResourceNotFoundException("No courses found for roll number: " + rollNum);
	        }

	        return count;
	    }

}
