package com.natthanan.findmyteacher.rowmapper;


import com.natthanan.findmyteacher.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<Teacher> {

    @Nullable
    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("id"));
        teacher.setName(resultSet.getString("name"));
        teacher.setRoom(resultSet.getString("room"));
        teacher.setTel(resultSet.getString("tel"));

        return teacher;
    }
}
