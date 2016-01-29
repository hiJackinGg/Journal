package com.mycompany.journal.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * managers are loaded lazy and it's not very good but it realize recursion.
 */

@Entity
@SqlResultSetMapping(name="ManagersWithCount",
        entities=@EntityResult(entityClass=Manager.class),
        columns = { @ColumnResult(name = "total")})
@NamedNativeQuery(name="Manager.findAllWithCount",
        query="SELECT a.*, b.total" +
                "        FROM manager a" +
                "        cross join (SELECT" +
                "        b.id," +
                "        COUNT(*) as total" +
                "        FROM manager b" +
                "        ) b limit ?1",
        resultSetMapping="ManagersWithCount")
public class Manager extends DomainObject {

    private String firstName;
    private String lastName;
    private String middleName;

    private String personnel;

    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subdivisionID")
    private Subdivision subdivision;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "positionID")
    private Position position;

    /**
     * Boss of the current manager
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentManagerID")
    Manager boss;

    /**
     *Subordinates of the current manager
     */
    @OneToMany(mappedBy = "boss")
    List<Manager> subordinates = new ArrayList<>();


    /**
     * managers which current manager(boss) delegated his subordinates
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "managerDelegatedFrom")
    private List<Manager> managerDelegatedTo = new ArrayList<>();


    /**
     * managers which subordinates are delegated to the current manager
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Delegation",
            joinColumns =
            @JoinColumn(name = "toManagerId"),
            inverseJoinColumns =
            @JoinColumn(name = "fromManagerId"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"toManagerId", "fromManagerId"})})
    private List<Manager> managerDelegatedFrom = new ArrayList<>();

    public Manager() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Subdivision getSector() {
        return subdivision;
    }

    public void setSector(Subdivision sector) {
        this.subdivision = sector;
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

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public Manager getBoss() {
        return boss;
    }

    public void setBoss(Manager boss) {
        this.boss = boss;
    }

    public List<Manager> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Manager> subordinates) {
        this.subordinates = subordinates;
    }

    public List<Manager> getDelegatedTo() {
        return managerDelegatedTo;
    }

    public void setDelegatedTo(List<Manager> managerDelegatedTo) {
        this.managerDelegatedTo = managerDelegatedTo;
    }

    public List<Manager> getDelegatedFrom() {
        return managerDelegatedFrom;
    }

    public void setDelegatedFrom(List<Manager> managerDelegatedFrom) {
        this.managerDelegatedFrom = managerDelegatedFrom;
    }

    public String getInitials(){
        return lastName + " " + firstName.charAt(0) + ". " + middleName.charAt(0) + ".";
    }
}
/*@OneToMany
@OrderColumn
@JoinColumn(name = "parent_id")*/