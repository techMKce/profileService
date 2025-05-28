package com.project.Profile.and.Enrollment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Profile.and.Enrollment.Entity.StudentEntity;
import com.project.Profile.and.Enrollment.Service.StudentService;

@RestController
@RequestMapping("api/v1/profile/student")
public class StudentController {
	
	@Autowired
    private StudentService studentService;

    @PostMapping
    public StudentEntity createStudentDetails(@RequestBody StudentEntity student) {
        return studentService.createStudent(student);
    }
    
    @GetMapping
    public List<StudentEntity> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{rollnum}")
    public StudentEntity getStudentDetails(@PathVariable String rollnum) {
        return studentService.getStudentByRollNum(rollnum);
    }

    @PutMapping("/{rollnum}")
    public StudentEntity updateStudentDetails(@PathVariable String rollnum, @RequestBody StudentEntity student) {
        return studentService.updateStudent(rollnum, student);
    }

    @GetMapping("/external-students")
    public String syncExternalStudents() {
        studentService.syncStudentsFromLoginService();
        return "Student data synced successfully!";
    }
	
	
}
