package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Reason extends DomainObject {

    private String name;

    private String note;

    public Reason() {
    }

    public Reason(String name, String note) {
        this.name = name;
        this.note = note;
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
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
