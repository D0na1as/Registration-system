package com.company.registration_system.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Specialist {

    @Id
    private int id;
    private String name;
    @Transient
    private String password;
    private String status;
    @Transient
    private String role;
    @Transient
    private boolean enabled;

    public Specialist() {
    }

    public Specialist(int id, String name, String password, String status, String role, boolean enabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.role = role;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
