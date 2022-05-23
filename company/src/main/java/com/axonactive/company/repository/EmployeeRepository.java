package com.axonactive.company.repository;

import com.axonactive.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByFirstNameLike(String firstName);

    List<Employee> findByFirstNameNotLike(String firstName);

    List<Employee> findByFirstNameAndLastNameLike(String firstName, String LastName);

    List<Employee> findEmployeeByFirstNameIgnoreCase(String firstName);

    List<Employee> findEmployeeByFirstNameContaining(String firstName);

    List<Employee> findEmployeeByFirstNameStartingWith(String startingLetter);

    List<Employee> findEmployeeByDateOfBirthBefore(LocalDate date);

//    List<Employee> findEmployeeByAgeLessThan(int age);

//    List<Employee> findEmployeeBySalaryOrderByLastNameDesc(Integer salary);
}

