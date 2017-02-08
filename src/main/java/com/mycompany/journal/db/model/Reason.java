package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Reason extends DomainObject {

    private String name;

    private String description;

    public Reason() {
    }

    public Reason(Long id) {
        this.id = id;
    }

    public Reason(String name, String note) {
        this.name = name;
        this.description = note;
    }

    public Reason(long id, String name) {
        this.id = id;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return description;
    }

    public void setNote(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reason{" +
                "name='" + name + '\'' +
                ", note='" + description + '\'' +
                '}';
    }
}


