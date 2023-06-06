package com.example.medicalapp.domain;

public class Section extends Entity<Integer>{

    private String name;

    private Hospital hospital;

    public Section(Integer id, String name, Hospital hospital) {
        setId(id);
        this.name = name;
        this.hospital = hospital;
    }
}
