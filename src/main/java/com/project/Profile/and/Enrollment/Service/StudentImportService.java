package com.project.Profile.and.Enrollment.Service;

import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class StudentImportService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean saveStudent(StudentEntity student) {
        try {
            studentRepository.save(student);
            return true;
        } catch (DataIntegrityViolationException e) {
            System.err.println("Data integrity violation for roll_num " + student.getRollNum() + ": " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error saving student " + student.getRollNum() + ": " + e.getMessage());
            return false;
        }
    }
}
