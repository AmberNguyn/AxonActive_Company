package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Employee;
import com.axonactive.company.repository.EmployeeRepository;
import com.axonactive.company.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAll()
    {
        return employeeRepository.findAll();
    }

    //C - create
    @Override
    public void saveEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }

    //R - retrieve
    @Override
    public Optional<Employee> findEmployeeById(Integer id)
    {
        return employeeRepository.findById(id);
    }

    //U - update

    //D - delete
    @Override
    public void deleteEmployee(String id)
    {
        employeeRepository.deleteById(Integer.valueOf(id));
    }
}
