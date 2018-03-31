package com.natthanan.findmyteacher.controller;

import com.natthanan.findmyteacher.model.Teacher;
import com.natthanan.findmyteacher.repository.CourseRepository;
import com.natthanan.findmyteacher.repository.CourseTeacherRepository;
import com.natthanan.findmyteacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTeacherRepository courseTeacherRepository;

    @GetMapping(path = "/")
    public String index() {
        return "Line Bot Test";
    }

    @GetMapping(path = "/teachers")
    public @ResponseBody Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping(path = "/courses/{courseId}")
    public @ResponseBody Iterable<Teacher> getAllTeacherIdByCourse(@PathVariable String courseId) {
        List<String> teacherList = courseTeacherRepository.findTeacherIdFromCourse(courseId);
        return teacherRepository.findAllById(teacherList);
    }
}
