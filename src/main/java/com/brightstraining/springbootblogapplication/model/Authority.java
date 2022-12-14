package com.brightstraining.springbootblogapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Authority implements Serializable {

    @Id
    @Column(length = 16)
    private String name;

    public Authority() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
//        return "Authority{" + "name='" + name + "'" + "}";
        return name;
    }
}
