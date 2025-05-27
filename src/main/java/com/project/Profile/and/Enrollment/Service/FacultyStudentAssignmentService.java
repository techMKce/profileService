package com.project.Profile.and.Enrollment.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Profile.and.Enrollment.Entity.CourseEnrollment;
import com.project.Profile.and.Enrollment.Entity.FacultyCourseKey;
import com.project.Profile.and.Enrollment.Entity.FacultyStudentAssignment;
import com.project.Profile.and.Enrollment.Exception.InvalidRequestException;
import com.project.Profile.and.Enrollment.Exception.ResourceNotFoundException;
import com.project.Profile.and.Enrollment.Repository.CourseEnrollmentRepository;
import com.project.Profile.and.Enrollment.Repository.FacultyStudentAssignmentRepository;

@Service
public class FacultyStudentAssignmentService {

    @Autowired
    private FacultyStudentAssignmentRepository repository;

    @Autowired
    private CourseEnrollmentRepository enrollmentRepo;



    public FacultyStudentAssignment assignStudentsManually(String facultyId, String courseId, List<String> rollNums) {
       
        CourseEnrollment enrollment = enrollmentRepo.findById(courseId)
        		.orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        List<String> enrolled = enrollment.getRollNums();

        
        for (String roll : rollNums) {
            if (!enrolled.contains(roll)) {
            	throw new InvalidRequestException("Roll number " + roll + " is not enrolled in course " + courseId);
            }
        }

        
        List<FacultyStudentAssignment> assignmentsForCourse = repository.findByIdCourseId(courseId);

       
        Set<String> assignedToOtherFaculties = new HashSet<>();
        for (FacultyStudentAssignment assignment : assignmentsForCourse) {
            if (!assignment.getId().getFacultyId().equals(facultyId)) {
                assignedToOtherFaculties.addAll(assignment.getAssignedRollNums());
            }
        }

        
      
        
        for (String roll : rollNums) {
            if (assignedToOtherFaculties.contains(roll)) {
            	throw new InvalidRequestException("Roll number " + roll + " is already assigned to another faculty for this course");
            }
        }

        
        FacultyCourseKey key = new FacultyCourseKey(facultyId, courseId);
        FacultyStudentAssignment assignment = repository.findById(key)
            .orElse(new FacultyStudentAssignment(facultyId, courseId, new ArrayList<>()));

        
        for (String roll : rollNums) {
            if (!assignment.getAssignedRollNums().contains(roll)) {
                assignment.getAssignedRollNums().add(roll);
            }
        }

        
        return repository.save(assignment);
    }

    
        
    public List<FacultyStudentAssignment> getAssignmentsByFaculty(String facultyId) {
    	List<FacultyStudentAssignment> assignments = repository.findByIdFacultyId(facultyId);
        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for faculty ID: " + facultyId);
        }
        return assignments;
    }

    public List<FacultyStudentAssignment> getAssignmentsByCourse(String courseId) {
    	List<FacultyStudentAssignment> assignments = repository.findByIdCourseId(courseId);
        if (assignments.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for course ID: " + courseId);
        }
        return assignments;
    }
    
    public int countStudentsAssignedToFaculty(String facultyId) {
        List<FacultyStudentAssignment> assignments = repository.findByIdFacultyId(facultyId);
        int totalCount = 0;
        for (FacultyStudentAssignment assignment : assignments) {
            totalCount += assignment.getAssignedRollNums().size();
        }
        return totalCount;
    }
    
    public int countCoursesAssignedToFaculty(String facultyId) {
        List<FacultyStudentAssignment> assignments = repository.findByIdFacultyId(facultyId);

        // Each assignment represents a (facultyId, courseId) pair, so just count them
        return assignments.size();
    }


}