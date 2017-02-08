package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Position extends DomainObject{

    private String name;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public Position(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Position{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
