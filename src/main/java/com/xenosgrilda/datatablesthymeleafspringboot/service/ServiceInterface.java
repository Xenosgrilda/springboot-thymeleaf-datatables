package com.xenosgrilda.datatablesthymeleafspringboot.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    List<T> findAllByOrderByLastNameAsc();
    Optional<T> findById(int id);
    void save(T newEmployee);
    void deleteById(int id);
}
