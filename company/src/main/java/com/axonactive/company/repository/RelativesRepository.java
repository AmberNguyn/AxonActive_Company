package com.axonactive.company.repository;

import com.axonactive.company.entity.Gender;
import com.axonactive.company.entity.Relatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelativesRepository extends JpaRepository<Relatives, Integer> {
    List<Relatives> findRelativesByFullName(String name);

    List<Relatives> findRelativesByGender(Gender gender);

    List<Relatives> findRelativesByPhoneNumberStartingWith(String phoneNumber);

    List<Relatives> findRelativesByRelationship(String relationship);

    Optional<Relatives> findRelativesByEmployeeEmployeeId(Integer employeeId);

    List<Relatives> findRelativesByEmployeeFirstName(String employeeName);

    List<Relatives> findRelativesByPhoneNumberContaining(String phoneNumber);

    List<Relatives> findRelativesByEmployeeSalaryLessThan(Integer salary);

    List<Relatives> findRelativesByFullNameStartingWithIgnoreCase(String name);




}
