package com.xenosgrilda.datatablesthymeleafspringboot.service;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import com.xenosgrilda.datatablesthymeleafspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements ServiceInterface{

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllByOrderByLastNameAsc() {
        return this.employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);

        Employee employee;

        if (employeeOptional.isPresent()){
            employee = employeeOptional.get();
        } else {
            throw new RuntimeException("Employee id: " + id + " was not found");
        }

        return employee;
    }

    @Override
    public void save(Employee newEmployee) {
        this.employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }
}
