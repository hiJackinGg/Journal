package com.mycompany.journal.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Service extends DomainObject{

    private String name;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

    @OneToMany(mappedBy = "service")
    private Collection<Sector> sectors = new ArrayList<>();;


    public Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Collection<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(Collection<Sector> sectors) {
        this.sectors = sectors;
    }
}
