package com.dkasiian.model.dao;

import java.util.List;

public interface GenericDao<T> {
    T getById(long id, String language);
    List<T> getAll(String language);
    boolean add(T item);
    void update(T item);
    boolean delete(long id);
}
