package com.xenosgrilda.datatablesthymeleafspringboot.service;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import com.xenosgrilda.datatablesthymeleafspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements ServiceInterface<Employee>{

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllByOrderByLastNameAsc() {
        return this.employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void save(Employee newEmployee) {
        this.employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByEmail(String email) {
        return this.employeeRepository.findByEmail(email);
    }
}
