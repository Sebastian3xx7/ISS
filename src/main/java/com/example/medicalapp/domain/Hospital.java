package com.example.medicalapp.domain;

public class Hospital extends Entity<Integer>{

    private String name;
    private String adress;

    public Hospital(Integer id, String name, String adress) {
        setId(id);
        this.name = name;
        this.adress = adress;
    }

    public void setId(Integer id){
        super.setId(id);
    }

    public Integer getIdHosp(){
        return getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
