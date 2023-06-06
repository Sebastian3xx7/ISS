package com.example.medicalapp.persistance.employees;

import com.example.medicalapp.domain.MedicalStaff;
import com.example.medicalapp.persistance.Repository;

import java.util.List;

public interface MedStaffRepo extends Repository<Integer, MedicalStaff> {
    MedicalStaff findBy(String username, String pass);
}
