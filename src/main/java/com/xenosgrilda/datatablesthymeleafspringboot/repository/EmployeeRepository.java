package com.xenosgrilda.datatablesthymeleafspringboot.repository;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom method to get data sorted
    List<Employee> findAllByOrderByLastNameAsc();
}

/*
    In Spring Data JPA we can use a certain namespace to get a method for free!
    findAllBy -> the classic
    OrderByLastNameAsc -> Spring will parse the method name.
 */