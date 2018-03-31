package com.natthanan.findmyteacher.repository;

import com.natthanan.findmyteacher.model.CourseTeacher;
import com.natthanan.findmyteacher.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableAutoConfiguration
@Repository
public class CourseTeacherRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> findTeacherIdFromCourse(String courseId) {
        return this.jdbcTemplate.queryForList("SELECT teacher_id FROM course_teacher WHERE course_id = ?", new Object[]{courseId}, String.class);
    }

}
