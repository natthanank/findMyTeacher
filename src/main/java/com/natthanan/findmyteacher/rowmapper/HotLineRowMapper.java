package com.natthanan.findmyteacher.rowmapper;


import com.natthanan.findmyteacher.model.HotLine;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotLineRowMapper implements RowMapper<HotLine> {

    @Nullable
    @Override
    public HotLine mapRow(ResultSet resultSet, int i) throws SQLException {
        HotLine HotLine = new HotLine();
        HotLine.setID(resultSet.getInt("id"));
        HotLine.setName(resultSet.getString("org_name"));
        HotLine.setTel(resultSet.getString("tel"));
        HotLine.setCategory(resultSet.getInt("category"));
        return HotLine;
    }
}
