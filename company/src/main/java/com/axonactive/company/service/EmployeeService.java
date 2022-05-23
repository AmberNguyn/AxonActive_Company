package com.axonactive.company.service;

import com.axonactive.company.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    //C - create
    public void saveEmployee(Employee employee);

    //R - retrieve
    public Optional<Employee> findEmployeeById(Integer id);

    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    public List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

    public List<Employee> findByFirstNameLike(String firstName);

    public List<Employee> findByFirstNameNotLike(String firstName);

    public List<Employee> findByFirstNameAndLastNameLike(String firstName, String LastName);

    public List<Employee> findEmployeeByFirstNameIgnoreCase(String firstName);

    public List<Employee> findEmployeeByFirstNameContaining(String firstName);

    public List<Employee> findEmployeeByFirstNameStartingWith(String startingLetter);

    public List<Employee> findEmployeeByDateOfBirthBefore(LocalDate date);

//    public List<Employee> findEmployeeByAgeLessThan(int age);

//    public List<Employee> findEmployeeBySalaryOrderByLastNameDesc(Integer salary);


    //U - update



    //D - delete
    public void deleteEmployee(String id);


}
