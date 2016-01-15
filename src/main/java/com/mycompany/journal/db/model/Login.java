package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Login extends DomainObject {

    private String name;

    @ManyToOne
    @JoinColumn(name = "ManagerID")
    private Manager manager;

    public Login() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
