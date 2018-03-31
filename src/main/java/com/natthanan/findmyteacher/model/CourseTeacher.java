package com.natthanan.findmyteacher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_teacher")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseTeacher {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "teacher_id")
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
