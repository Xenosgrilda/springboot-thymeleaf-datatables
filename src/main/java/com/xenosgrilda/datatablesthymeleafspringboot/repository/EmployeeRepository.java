package com.xenosgrilda.datatablesthymeleafspringboot.repository;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom method to get data sorted
    List<Employee> findAllByOrderByLastNameAsc();

    Optional<Employee> findByEmail(String email);

    @Query( "FROM Employee e WHERE LOWER(e.lastName) LIKE %:searchTerm%")
    Page<Employee> findAllSearch(
           @Param("searchTerm") String searchTerm,
           Pageable pageable);

    @Query("SELECT count(e.id) FROM Employee e")
    long countRows();

//    List<Employee> findPageableList(int page, int size);
}

/*
    In Spring Data JPA we can use a certain namespace to get a method for free!
    findAllBy -> the classic
    OrderByLastNameAsc -> Spring will parse the method name.
 */