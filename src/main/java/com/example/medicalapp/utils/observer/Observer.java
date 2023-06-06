package com.example.medicalapp.utils.observer;


import com.example.medicalapp.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}