package com.project.Profile.and.Enrollment.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Profile.and.Enrollment.Entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {
	
	Optional<StudentEntity> findByRollNum(String rollNum);

}
