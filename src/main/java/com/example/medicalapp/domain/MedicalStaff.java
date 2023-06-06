package com.example.medicalapp.domain;

import javax.persistence.Table;

@javax.persistence.Entity
@Table( name = "MedicalStaff")
public class MedicalStaff extends Employee{
    private Integer idSection;


    public MedicalStaff(Integer id, String username, String password, Integer section) {
        super(id, username, password);
        this.idSection = section;
    }

    public MedicalStaff() {
        super();
    }

    public Integer getSection() {
        return idSection;
    }

    public void setSection(Integer section) {
        this.idSection = section;
    }
}
