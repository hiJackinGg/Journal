package com.mycompany.journal.db.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    @NotEmpty(message = "FirstName can't be empty")
    private String firstName;
    @NotEmpty(message = "LastName can't be empty")
    private String lastName;
    private String middleName;

    @Pattern(regexp = "[a-z]")
    private String personnel;

    @Email()
    private String email;

    @ManyToOne()
    @JoinColumn(name = "subdivisionID")
    private Subdivision subdivision;

    @ManyToOne()
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "boss")
    @Fetch(value = FetchMode.SUBSELECT)
    List<Manager> subordinates = new ArrayList<>();

    /**
     * managers which current manager(boss) delegated his subordinates
     */
    @ManyToMany( mappedBy = "managerDelegatedFrom")
    private List<Manager> managerDelegatedTo = new ArrayList<>();;

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
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Manager> managerDelegatedFrom = new ArrayList<>();;

    public Manager() {
    }

    public Manager(long id) {

        this.id = id;
    }

    public List<Manager> getSubordinates() {
        return Collections.unmodifiableList(subordinates);
    }

    public List<Manager> getDelegatedTo() {
        return Collections.unmodifiableList(managerDelegatedTo);
    }

    public List<Manager> getDelegatedFrom() {
        return Collections.unmodifiableList(managerDelegatedFrom);
    }

    public void addSubordinate(Manager manager) {
        subordinates.add(manager);
    }

    public void addManagerDelegatedTo(Manager manager) {
        managerDelegatedFrom.add(manager);
    }

    public void addManagerDelegatedFrom(Manager manager) {
        managerDelegatedFrom.add(manager);
    }

   public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public void setSubordinates(List<Manager> subordinates) {
        this.subordinates = subordinates;
    }

    public void setDelegatedTo(List<Manager> managerDelegatedTo) {
        this.managerDelegatedTo = managerDelegatedTo;
    }

    public void setDelegatedFrom(List<Manager> managerDelegatedFrom) {
        this.managerDelegatedFrom = managerDelegatedFrom;
    }

    @Transient
    public String getInitials(){

        if(lastName != null && firstName != null && middleName != null)

            return lastName + " " +
                    firstName.charAt(0) + ". " +
                    middleName.charAt(0) + ".";
        else

            return "";
    }



    @Override
    public int compareTo(DomainObject that) {

        if (that == null){
            return super.compareTo(that);
        }

        Manager thatManager = (Manager) that;

        if (this.subdivision != null && thatManager.getSubdivision() != null)
            if (this.subdivision.getLevel() != null &&
                    thatManager.getSubdivision().getLevel() != null)
                return this.subdivision.getLevel().toInteger() -
                        thatManager.getSubdivision().getLevel().toInteger();

            return super.compareTo(that);

    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Manager{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", personnel='").append(personnel).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", subdivision=").append(subdivision.getName());
        sb.append(", position=").append(position.getName());
        sb.append('}');
        return sb.toString();
    }
}
