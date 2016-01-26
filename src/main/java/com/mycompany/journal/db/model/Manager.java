package com.mycompany.journal.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "subdivisionID")
    private Subdivision subdivision;

    @ManyToOne
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
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "delegatedFrom")
    private List<Manager> delegatedTo = new ArrayList<>();


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
    private List<Manager> delegatedFrom = new ArrayList<>();

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

    public String getInitials(){
        return lastName + " " + firstName.charAt(0) + ". " + middleName.charAt(0) + ".";
    }
}
/*@OneToMany
@OrderColumn
@JoinColumn(name = "parent_id")*/