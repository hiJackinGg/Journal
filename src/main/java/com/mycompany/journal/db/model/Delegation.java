package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Delegation extends DomainObject {

    @ManyToOne
    @JoinColumn(name = "fromManagerId")
    private Manager manager1;

    @ManyToOne
    @JoinColumn(name = "toManagerId")
    private Manager manager2;


    public Delegation() {
    }

    public Manager getManager1() {
        return manager1;
    }

    public void setManager1(Manager manager1) {
        this.manager1 = manager1;
    }

    public Manager getManager2() {
        return manager2;
    }

    public void setManager2(Manager manager2) {
        this.manager2 = manager2;
    }
}
