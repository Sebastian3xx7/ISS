package com.example.medicalapp.domain;

public class Pharmacy extends Entity<Integer>{

    String name;
    String adress;

    public Pharmacy(Integer id, String name, String adress) {
        setId(id);
        this.name = name;
        this.adress = adress;
    }
}
