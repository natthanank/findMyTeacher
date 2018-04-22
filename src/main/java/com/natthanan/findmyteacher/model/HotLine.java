package com.natthanan.findmyteacher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "HotLine")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotLine {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "org_name")
    private String name;
    @Column(name = "tel")
    private String tel;
    @Column(name = "category")
    private int category;

    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }

    public void setName(String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTel(String tel ) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setCategory(int category ) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
