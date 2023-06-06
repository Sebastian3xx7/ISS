package com.example.medicalapp.persistance.pharmacy;

import com.example.medicalapp.domain.Stock;
import com.example.medicalapp.persistance.Repository;

public interface StockRepo extends Repository<Integer, Stock> {
    void updateByFields(String medicine, Integer quan);
    void updateByDecreasing(String medicine, Integer quan);
    Stock findByMed(String med);


}
