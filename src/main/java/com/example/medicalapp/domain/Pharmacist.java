package com.example.medicalapp.domain;

import javax.persistence.Table;

@javax.persistence.Entity
@Table( name = "Pharmacists")
public class Pharmacist extends Employee{
    Integer idPharmacy;

    public Pharmacist(Integer id, String username, String password, Integer pharmacy) {
        super(id, username, password);
        this.idPharmacy = pharmacy;
    }

    public Pharmacist() {
        super();
    }

    public Integer getIdPharmacy() {
        return idPharmacy;
    }

    public void setIdPharmacy(Integer idPharmacy) {
        this.idPharmacy = idPharmacy;
    }
}
