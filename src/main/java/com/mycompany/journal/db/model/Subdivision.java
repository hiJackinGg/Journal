package com.mycompany.journal.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subdivision extends DomainObject {


    private String name;

    @Enumerated(EnumType.STRING)
    private SubdivisionLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentSubdivisionID")
    Subdivision parent;

    @OneToMany(mappedBy = "parent")
    List<Subdivision> children = new ArrayList<>();


    public Subdivision() {
    }

    public Subdivision(String name) {
        this.name = name;
    }

    public Subdivision(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subdivision> getChildren() {
        return children;
    }

    public void setChildren(List<Subdivision> children) {
        this.children = children;
    }

    public Subdivision getParent() {
        return parent;
    }

    public void setParent(Subdivision parent) {
        this.parent = parent;
    }

    public SubdivisionLevel getLevel() {
        return level;
    }

    public void setLevel(SubdivisionLevel level) {
        this.level = level;
    }
}
