package com.mycompany.journal.db.model;

import com.mycompany.journal.validation.MyLogpresenceAnnotation;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Date;


@Entity
@MyLogpresenceAnnotation
public class Logpresence extends DomainObject {


    //@NotNull(message="Date absence can't be null")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDateTime dateAbsence;

    private Integer latenessTime;

    private String note;

    @NotNull(message="Manager can't be null")
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

    public LocalDateTime getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(LocalDateTime dateAbsence) {
        this.dateAbsence = dateAbsence;
    }

    @Transient
    public String getBirthDateString() {
        String dateAbsenceString = "";
        if (dateAbsence != null)
            dateAbsenceString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(dateAbsence);
        return dateAbsenceString;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Logpresence{");
        sb.append("dateAbsence=").append(dateAbsence);
        sb.append(", latenessTime=").append(latenessTime);
        sb.append(", note='").append(note).append('\'');
        sb.append(", manager=").append(manager);
        sb.append(", reason=").append(reason);
        sb.append('}');
        return sb.toString();
    }
}
