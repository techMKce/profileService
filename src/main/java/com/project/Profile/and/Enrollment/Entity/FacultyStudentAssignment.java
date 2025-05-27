package com.project.Profile.and.Enrollment.Entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty_student_assignment")
public class FacultyStudentAssignment {

    @EmbeddedId
    private FacultyCourseKey id;

    @ElementCollection
    private List<String> assignedRollNums;

    public FacultyStudentAssignment() {}

    public FacultyStudentAssignment(String facultyId, String courseId, List<String> assignedRollNums) {
        this.id = new FacultyCourseKey(facultyId, courseId);
        this.assignedRollNums = assignedRollNums;
    }

    public FacultyCourseKey getId() {
        return id;
    }

    public void setId(FacultyCourseKey id) {
        this.id = id;
    }

    public List<String> getAssignedRollNums() {
        return assignedRollNums;
    }

    public void setAssignedRollNums(List<String> assignedRollNums) {
        this.assignedRollNums = assignedRollNums;
    }

    public String getFacultyId() {
        return id.getFacultyId();
    }

    public String getCourseId() {
        return id.getCourseId();
    }
}