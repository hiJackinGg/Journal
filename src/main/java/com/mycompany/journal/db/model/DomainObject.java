package com.mycompany.journal.db.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@MappedSuperclass
public abstract class DomainObject implements Comparable<DomainObject> {

    //@NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public DomainObject() {
    }
    public DomainObject(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(DomainObject that) {
        return (int) (this.id - that.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DomainObject other = (DomainObject) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;

    }

    @Override
    public String toString() {
        return "DomainObject{" +
                "id=" + id +
                '}';
    }
}
