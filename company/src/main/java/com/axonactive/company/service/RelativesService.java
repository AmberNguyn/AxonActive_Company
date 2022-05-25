package com.axonactive.company.service;

import com.axonactive.company.entity.Relatives;

import java.util.List;
import java.util.Optional;

public interface RelativesService {
    List<Relatives> getAll();

    //C - create
    public void saveRelatives(Relatives relatives);

    //R - retrieve
    public Optional<Relatives> findRelativeById(Integer id);

    public List<Relatives> findRelativesByFullName(String name);

    public List<Relatives> findRelativesByGender(String gender);

    public List<Relatives> findRelativesByPhoneNumberStartingWith(String phoneNumber);

    public List<Relatives> findRelativesByRelationship(String relationship);

    public Optional<Relatives> findRelativesByEmployeeEmployeeId(Integer employeeId);

    public List<Relatives> findRelativesByEmployeeFirstName(String employeeName);

    public List<Relatives> findRelativesByPhoneNumberContaining(String phoneNumber);

    public List<Relatives> findRelativesByEmployeeSalaryLessThan(Integer salary);

    public List<Relatives> findRelativesByFullNameStartingWithIgnoreCase(String name);


    //U - update


    //D - delete
    public void deleteRelative(Integer id);
}
