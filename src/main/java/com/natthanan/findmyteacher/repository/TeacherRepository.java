package com.natthanan.findmyteacher.repository;

import com.natthanan.findmyteacher.model.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, String> {
}
