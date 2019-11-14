package com.xenosgrilda.datatablesthymeleafspringboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import com.xenosgrilda.datatablesthymeleafspringboot.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/employees-tables-mvc")
public class EmployeeControllerPageableMVC {

    private final int maxPage = 10;

    private EmployeeService employeeService;

    @GetMapping("")
    public String employeeRedirect() {
        return "redirect:/employees-tables-mvc/home";
    }

// Model approach
//    @GetMapping("/home")
//    public String getEmployeesList(Model employees) {
//
//        List<Employee> employeeList = this.employeeService.findAllByOrderByLastNameAsc();
//
//        employees.addAttribute("employees", employeeList);
//
//        return "employee/home";
//    }

    // Pageable MVC (Per Request)
    @GetMapping("/home")
    public ModelAndView getEmployeesList(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "1") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "5") int size
    )
    {
        // Pagination content
        long rowCount = this.employeeService.countRows2();
        Page<Employee> employeePage = this.employeeService.findAllPageable(page -1, size);

        ModelAndView modelAndView = new ModelAndView("employee/home-pageable-mvc");
        modelAndView.addObject("employees", employeePage);

        // Pages amount
        int pageAmount = employeePage.getTotalPages();

        List<Integer> pageAmountList;

        if (pageAmount > 0){
            int start, end;

            if (page >= 10){
                start = Math.max(1, page - 4);
                end = Math.min(pageAmount, (page + 5));
            } else {
                start = 1;
                end = Math.min(pageAmount, 10);
            }

            pageAmountList = IntStream.rangeClosed(start, end)
            .boxed()
            .collect(Collectors.toList());
        }
        else {

            pageAmountList = new ArrayList<>();
            pageAmountList.add(1);
        }

        modelAndView.addObject("pageAmountList", pageAmountList);
        return modelAndView;
    }

    @GetMapping("/edit-form/{id}")
    public ModelAndView editFormEmployee(@PathVariable int id, Employee emp){

        if ( id == -1){
            emp.setId(id);
            return new ModelAndView("employee/edit").addObject("employee",emp);

        }

        else {

            Optional<Employee> entity = this.employeeService.findById(emp.getId());

            if (entity.isPresent()){

                return new ModelAndView("employee/edit")
                        .addObject("employee", entity.get());
            }

            else {

                return this.getEmployeesList(0, 5).addObject("message",
                        "The employee id: " + emp.getId() + " don't exists.");
            }

        }
    }

    @GetMapping("/create")
    public ModelAndView createEmployee() {
        Employee emp = new Employee();
        return this.editFormEmployee(-1, emp);
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(value = "id") int id) {

        this.employeeService.deleteById(id);

        return "redirect:/employees-tables-mvc/home";
    }

    @PostMapping("/edit")
    public ModelAndView editEmployee(@Valid Employee employee, BindingResult bindingResult) {


        if (bindingResult.hasErrors()){

            return this.editFormEmployee(-1,employee);

        } else {

            // Checking if there is an employee with this email
            Optional<Employee> entity = this.employeeService.findByEmail(employee.getEmail());

            if (!entity.isPresent()){

                employeeService.save(employee);

                return new ModelAndView("redirect:/employees-tables-mvc/home");
            }

            else {

                return this.getEmployeesList(0, 5).addObject("message",
                        "The email: " + employee.getEmail() + " is already in use.");
            }
        }
    }
}

/**
 * On "editEmployee" @ModelAttribute receives the name of the Model being sent from the view
*/

 /**
  * Remember that the name of the object "nameOfTheObject" added in "ModelAndView("...").addObject("nameOfTheObject", object)" needs to
  * be the same as object's class, otherwise BindingResult will get lost.
*/