package com.xenosgrilda.datatablesthymeleafspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "id")
    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Coloca um valor ai cumpadi")
    private String lastName;

    @Email(regexp = "^(.+)@(.+)$", message = "ENTER VALID EMAIL AAAAAAAAAAAH!")
    @Column(name = "email", unique = true)
    private String email;

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return  "ID: " + this.id +"\n" +
                "First Name: " + this.firstName +"\n" +
                "Last Name: " + this.lastName + "\n" +
                "Email: " + this.email;
    }
}