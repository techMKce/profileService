package com.project.Profile.and.Enrollment.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.Profile.and.Enrollment.Entity.CourseEnrollment;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, String> {
	
	@Query("SELECT c FROM CourseEnrollment c WHERE :rollNum MEMBER OF c.rollNums")
    List<CourseEnrollment> findByRollNum(@Param("rollNum") String rollNum);
}
