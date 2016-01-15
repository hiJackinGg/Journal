package com.mycompany.journal.db.model;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Logpresence extends DomainObject {

    @Temporal(TemporalType.DATE)
    private Date dateAbsence;

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

    public Date getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(Date dateAbsence) {
        this.dateAbsence = dateAbsence;
    }
}
