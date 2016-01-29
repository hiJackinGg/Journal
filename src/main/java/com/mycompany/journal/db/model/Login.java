package com.mycompany.journal.db.model;

import com.sun.javafx.beans.annotations.Default;

import javax.persistence.*;
import java.util.List;

@Entity
public class Login extends DomainObject {

    private String username;

    private String password;

    private Boolean enabled = true;

    @ElementCollection()
    @CollectionTable(name = "Roles", joinColumns = @JoinColumn(name = "loginID"))
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "ManagerID")
    private Manager manager;

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
}
