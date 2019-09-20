package com.xenosgrilda.datatablesthymeleafspringboot.service;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;

import java.util.List;

public interface ServiceInterface {
    List<Employee> findAllByOrderByLastNameAsc();
    Employee findById(int id);
    void save(Employee newEmployee);
    void deleteById(int id);
}
