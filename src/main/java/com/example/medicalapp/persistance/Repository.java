package com.example.medicalapp.persistance;


import com.example.medicalapp.domain.Entity;

public interface Repository<ID, E extends Entity<ID>> {

    void save(E e);
    void delete(ID id);
    E findOne(ID id);
    void update(ID id, E e);
    Iterable<E> getAll();
}
