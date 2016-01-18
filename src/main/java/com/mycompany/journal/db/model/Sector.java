package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Sector extends DomainObject {

    private String name;

    @ManyToOne
    @JoinColumn(name = "ServiceID")
    private Service service;


    public Sector() {
    }

    public Sector(String name) {
        this.name = name;
    }

    public Sector(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
