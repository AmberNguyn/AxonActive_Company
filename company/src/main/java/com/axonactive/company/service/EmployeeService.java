package com.axonactive.company.service;

import com.axonactive.company.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    //C - create
    public void saveEmployee(Employee employee);

    //R - retrieve
    public Optional<Employee> findEmployeeById(Integer id);

    //U - update



    //D - delete
    public void deleteEmployee(String id);
}
