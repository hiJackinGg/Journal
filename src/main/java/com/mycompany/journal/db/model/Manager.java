package com.mycompany.journal.db.model;

import javax.persistence.*;

@Entity
public class Manager extends DomainObject {

    private String firstName;
    private String lastName;
    private String middleName;

    private String personnel;

    private String email;

    @ManyToOne
    @JoinColumn(name = "sectorID")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "positionID")
    private Position position;

    public Manager() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInitials(){
        return lastName + " " + firstName.charAt(0) + ". " + middleName.charAt(0) + ".";
    }
}
/*@OneToMany
@OrderColumn
@JoinColumn(name = "parent_id")*/