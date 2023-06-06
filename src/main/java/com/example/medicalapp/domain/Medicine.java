package com.example.medicalapp.domain;

public class Medicine extends Entity<Integer>{

    private String name;
    private String prospect;

    public Medicine(Integer id, String name, String prospect) {
        setId(id);
        this.name = name;
        this.prospect = prospect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProspect() {
        return prospect;
    }

    public void setProspect(String prospect) {
        this.prospect = prospect;
    }
}
