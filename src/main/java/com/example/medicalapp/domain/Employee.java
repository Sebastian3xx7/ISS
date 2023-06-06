package com.example.medicalapp.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Employee extends Entity<Integer>{
    private String username;
    private String password;

    public Employee(Integer id, String username, String password) {
        setId(id);
        this.username = username;
        this.password = password;
    }

    public Employee() {

    }

    public Integer getIdEm() {
        return getId();
    }

    public void setId(Integer id) {
        super.setId(id);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
