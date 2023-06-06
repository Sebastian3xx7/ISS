package com.example.medicalapp.persistance.employees;

import com.example.medicalapp.domain.MedicalStaff;
import com.example.medicalapp.domain.Pharmacist;
import com.example.medicalapp.persistance.Repository;

import java.util.List;

public interface PharmacistRepo extends Repository<Integer, Pharmacist> {
    Pharmacist findBy(String username, String pass);
}
