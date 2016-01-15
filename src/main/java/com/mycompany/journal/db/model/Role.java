package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Role extends DomainObject {

    private String name;

    /*@OneToMany()
    private Collection<Manager> managers = new ArrayList<>();*/

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
