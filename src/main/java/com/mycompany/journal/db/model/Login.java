package com.mycompany.journal.db.model;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class Login extends DomainObject {

    @Column(unique=true)
    private String username;

    @NotNull(message="Login password can't be null")
    @Size(min=5, max=10, message="Login password range must be between 5 and 10 chars ")
    private String password;

    //@Column(columnDefinition = "tinyint(1) default '1'")
    private Boolean enabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Roles", joinColumns = @JoinColumn(name = "loginID"))
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @NotNull(message="Manager can't be null")
    @ManyToOne
    @JoinColumn(name = "ManagerID")
    private Manager manager;

    /**
     * last login date
     */
    @NotNull(message="Login date can't be null")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime loginDate;

    public Login() {
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Login{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", enabled=").append(enabled);
        sb.append(", roles=").append(roles);
        sb.append(", manager=").append(manager);
        sb.append(", loginDate=").append(loginDate);
        sb.append('}');
        return sb.toString();
    }
}
