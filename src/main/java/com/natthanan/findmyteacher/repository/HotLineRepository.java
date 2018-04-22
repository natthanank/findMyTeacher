package com.natthanan.findmyteacher.repository;

import java.util.List;

import com.natthanan.findmyteacher.model.HotLine;
import com.natthanan.findmyteacher.rowmapper.HotLineRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@EnableAutoConfiguration
@Repository
public class HotLineRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<HotLine> findHotLineByCategory(int category) {
        return this.jdbcTemplate.query("SELECT id, org_name, tel, category FROM HotLine WHERE category = ?", new Object[]{category}, new HotLineRowMapper());
    }

}
