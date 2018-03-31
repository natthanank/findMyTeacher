package com.natthanan.findmyteacher.repository;

import com.natthanan.findmyteacher.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, String> {
}
