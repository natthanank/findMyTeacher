package com.natthanan.findmyteacher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private String id;
    @Column(name = "teacher_name")
    private String name;
    @Column(name = "teacher_tel")
    private String tel;
    @Column(name = "teacher_room")
    private String room;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
