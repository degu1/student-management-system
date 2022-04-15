package se.iths.service;

import se.iths.exception.IdNotFoundException;

import java.util.List;

public interface Service<T> {
    void create(T t);

    void updateWithPUT(T t);

    void updateWithPATCH(T t) throws IdNotFoundException;

    List<T> getAll();

    T getById(Long id) throws IdNotFoundException;

    void remove(Long id) throws IdNotFoundException;
}
