package com.mycompany.journal.db.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Logpresence extends DomainObject {

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime dateAbsence;

    private Integer latenessTime;

    private String note;

    @ManyToOne
    @JoinColumn(name = "managerID")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "reasonID")
    private Reason reason;

    public Logpresence() {
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLatenessTime() {
        return latenessTime;
    }

    public void setLatenessTime(Integer latenessTime) {
        this.latenessTime = latenessTime;
    }

    public DateTime getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(DateTime dateAbsence) {
        this.dateAbsence = dateAbsence;
    }
}
