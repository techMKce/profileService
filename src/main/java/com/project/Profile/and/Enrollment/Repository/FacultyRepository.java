package com.project.Profile.and.Enrollment.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Profile.and.Enrollment.Entity.FacultyEntity;
import org.springframework.data.jpa.repository.Query;

public interface FacultyRepository extends JpaRepository<FacultyEntity,String>{

	Optional<FacultyEntity> findByStaffId(String staffId);
	List<FacultyEntity> findByDepartmentIgnoreCase(String department);


	@Query("SELECT DISTINCT f.department FROM FacultyEntity f")
	List<String> findDistinctDepartments();
}
