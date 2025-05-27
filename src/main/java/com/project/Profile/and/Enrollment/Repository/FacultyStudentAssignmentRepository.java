package com.project.Profile.and.Enrollment.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Profile.and.Enrollment.Entity.FacultyCourseKey;
import com.project.Profile.and.Enrollment.Entity.FacultyStudentAssignment;

@Repository
public interface FacultyStudentAssignmentRepository extends JpaRepository<FacultyStudentAssignment, FacultyCourseKey> {
    List<FacultyStudentAssignment> findByIdFacultyId(String facultyId);
    List<FacultyStudentAssignment> findByIdCourseId(String courseId);
}