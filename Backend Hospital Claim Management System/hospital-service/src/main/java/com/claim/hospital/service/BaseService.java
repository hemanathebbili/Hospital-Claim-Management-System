package com.claim.hospital.service;

import java.util.List;

public interface BaseService<T, ID> {

    T create(T t);

    T getById(ID id);

    List<T> getAll();

    void delete(ID id);
}
