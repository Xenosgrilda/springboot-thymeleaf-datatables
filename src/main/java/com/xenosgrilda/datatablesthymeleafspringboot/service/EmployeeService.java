package com.xenosgrilda.datatablesthymeleafspringboot.service;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import com.xenosgrilda.datatablesthymeleafspringboot.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements ServiceInterface<Employee>{

    private EmployeeRepository employeeRepository;

    // Interface Basic Methods

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

    // Custom Methods
    public Optional<Employee> findByEmail(String email) {
        return this.employeeRepository.findByEmail(email);
    }

    // Pageable returning everything
    public Page<Employee> findAllPageable(int page, int size) {

        List<Employee> resultList = this.employeeRepository.findAll();

        if (page > (resultList.size() / size)){

            page = (resultList.size() / size);
        }

        if (size >= resultList.size()){
            page = 0;
        }


        PageRequest pageRequest = PageRequest.of(page, size);

        PagedListHolder<Employee> pagedListHolder = new PagedListHolder<>(resultList);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(size);

        // Turning a list into Page
        return new PageImpl<>(pagedListHolder.getPageList(), pageRequest, resultList.size());
    }

    // TODO: Page fetching only the relevant content
//    public Page<Employee> findAllPageable(int page, int size) {
//
//        // Fetching data
//        List<Employee> resultList = this.employeeRepository.findAll();
//
//        PageRequest pageRequest = PageRequest.of(page, size);
//        PagedListHolder<Employee> pagedListHolder = new PagedListHolder<>();
//
//        pagedListHolder.setSource(resultList);
//        pagedListHolder.setPageSize(size);
//        pagedListHolder.setPage(page);
//
//        // Turning a list into Page
//        return new PageImpl<>(pagedListHolder.getPageList(), pageRequest, resultList.size());
//    }

    public Page<Employee> findAllPageableRest(int page, int size, String searchTerm) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "lastName");

        return this.employeeRepository.findAllSearch(searchTerm, pageRequest);
    }

    // Alternatives to count
    public long countRows() {
        return this.employeeRepository.countRows();
    }

    public long countRows2() {
        return this.employeeRepository.count();
    }
}
