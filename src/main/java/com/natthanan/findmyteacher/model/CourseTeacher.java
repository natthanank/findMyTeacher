package com.natthanan.findmyteacher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_teacher")
public class CourseTeacher {

    @Id
    @Column(name = "courseId")
    private String courseId;
    @Column(name = "teacherId")
    private Integer teacherId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
