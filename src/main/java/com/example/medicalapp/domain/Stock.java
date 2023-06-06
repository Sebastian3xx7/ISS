package com.example.medicalapp.domain;

import javax.persistence.Table;

@javax.persistence.Entity
@Table( name = "MedicalStaff")
public class Stock extends Entity<Integer>{

    Integer idPharmacy;
    String medicine;
    String stockKeepingUnit;
    Integer quantity;

    public Stock(Integer id, Integer idPharmacy, String Medicine, String stockKeepingUnit, Integer quantity) {
        setId(id);
        this.idPharmacy = idPharmacy;
        this.medicine = Medicine;
        this.stockKeepingUnit = stockKeepingUnit;
        this.quantity = quantity;
    }

    public Stock() {

    }

    public Integer getIdPharmacy() {
        return idPharmacy;
    }

    public void setIdPharmacy(Integer idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String idMedicine) {
        this.medicine = idMedicine;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
