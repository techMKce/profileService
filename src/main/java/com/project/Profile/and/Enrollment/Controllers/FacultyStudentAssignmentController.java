package com.project.Profile.and.Enrollment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Profile.and.Enrollment.Dto.FacultyStudentAssignmentDto;
import com.project.Profile.and.Enrollment.Entity.FacultyStudentAssignment;
import com.project.Profile.and.Enrollment.Service.FacultyStudentAssignmentService;

@RestController
@RequestMapping("/api/v1/faculty-student-assigning/admin")
public class FacultyStudentAssignmentController {

    @Autowired
    private FacultyStudentAssignmentService service;

    @PostMapping("/assign")
    public FacultyStudentAssignment assignStudents(@RequestBody FacultyStudentAssignmentDto dto) {
        return service.assignStudentsManually(dto.getFacultyId(), dto.getCourseId(), dto.getRollNums());
    }

    @GetMapping("/faculty/{facultyId}")
    public List<FacultyStudentAssignment> getAssignmentsByFaculty(@PathVariable String facultyId) {
        return service.getAssignmentsByFaculty(facultyId);
    }

    @GetMapping("/course/{courseId}")
    public List<FacultyStudentAssignment> getAssignmentsByCourse(@PathVariable String courseId) {
        return service.getAssignmentsByCourse(courseId);
    }
    
    //total count of student assigned to a  particular faculty
    @GetMapping("/faculty/{facultyId}/count")
    public int countStudentsByFaculty(@PathVariable String facultyId) {
        return service.countStudentsAssignedToFaculty(facultyId);
    }
    
    
   //total number of course count assigned to a faculty
    @GetMapping("/faculty/{facultyId}/course-count")
    public int countCoursesAssignedToFaculty(@PathVariable String facultyId) {
        return service.countCoursesAssignedToFaculty(facultyId);
    }

    @DeleteMapping("/unassign-all/{rollNum}")
    public ResponseEntity<String> removeStudentFromAllFaculties(@PathVariable String rollNum) {
        service.removeStudentFromAllFaculties(rollNum);
        return ResponseEntity.ok("Student " + rollNum + " removed from all faculty assignments.");
    }

    
}
