package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Employee;
import com.axonactive.company.repository.EmployeeRepository;
import com.axonactive.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    //C - create
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    //R - retrieve
    @Override
    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }


    //U - update









    //D - delete
    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(Integer.valueOf(id));
    }

    //Query
    @Override
    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Employee> findByFirstNameOrLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    @Override
    public List<Employee> findByFirstNameLike(String firstName) {
        return employeeRepository.findByFirstNameLike(firstName);
    }

    @Override
    public List<Employee> findByFirstNameNotLike(String firstName) {
        return employeeRepository.findByFirstNameNotLike(firstName);
    }

    @Override
    public List<Employee> findByFirstNameAndLastNameLike(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastNameLike(firstName, lastName);
    }

    @Override
    public List<Employee> findEmployeeByFirstNameIgnoreCase(String firstName) {
        return employeeRepository.findEmployeeByFirstNameIgnoreCase(firstName);
    }

    @Override
    public List<Employee> findEmployeeByFirstNameContaining(String firstName) {
        return employeeRepository.findEmployeeByFirstNameContaining(firstName);
    }

    @Override
    public List<Employee> findEmployeeByFirstNameStartingWith(String startingLetter) {
        return employeeRepository.findEmployeeByFirstNameStartingWith(startingLetter);
    }

    @Override
    public List<Employee> findEmployeeByDateOfBirthBefore(LocalDate date) {
        return employeeRepository.findEmployeeByDateOfBirthBefore(date);
    }

    @Override
    public List<Employee> findEmployeeByDateOfBirthBetween(LocalDate startingDate, LocalDate endingDate) {
        return employeeRepository.findEmployeeByDateOfBirthBetween(startingDate, endingDate);
    }






    @Override
    public List<Employee> findEmployeeBySalaryOrderByLastNameDesc(Integer salary)
    {
        return employeeRepository.findEmployeeBySalaryOrderByLastNameDesc(salary);
    }


}
