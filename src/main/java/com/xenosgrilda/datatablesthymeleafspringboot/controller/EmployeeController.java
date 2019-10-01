package com.xenosgrilda.datatablesthymeleafspringboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.xenosgrilda.datatablesthymeleafspringboot.entity.Employee;
import com.xenosgrilda.datatablesthymeleafspringboot.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("")
    public String employeeRedirect() {
        return "redirect:/employees/home";
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

    @GetMapping("/home")
    public ModelAndView getEmployeesList() {


        ModelAndView modelAndView = new ModelAndView("employee/home");
        List<Employee> employeeList = this.employeeService.findAllByOrderByLastNameAsc();

        modelAndView.addObject("employees", employeeList);

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

                return this.getEmployeesList().addObject("message",
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

        return "redirect:/employees/home";
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

                // Faking home
                List<Employee> employeeList = this.employeeService.findAllByOrderByLastNameAsc();

                return new ModelAndView("redirect:/employees/home").addObject("employees", employeeList);
            }

            else {

                return this.getEmployeesList().addObject("message",
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