package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Gender;
import com.axonactive.company.entity.Relatives;
import com.axonactive.company.repository.RelativesRepository;
import com.axonactive.company.service.RelativesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelativesServiceImpl implements RelativesService {
    private final RelativesRepository relativesRepository;

    @Override
    public List<Relatives> getAll()
    {
        return relativesRepository.findAll();
    }

    //C - create
    @Override
    public void saveRelatives(Relatives relatives){
        relativesRepository.save(relatives);
    }

    //R - retrieve
    @Override
    public Optional<Relatives> findRelativeById(Integer id)
    {
        return relativesRepository.findById(id);
    }

    @Override
    public List<Relatives> findRelativesByFullName(String name)
    {
        return relativesRepository.findRelativesByFullName(name);
    }

    @Override
    public List<Relatives> findRelativesByGender(String gender)
    {
        return relativesRepository.findRelativesByGender(Gender.valueOf(gender));
    }

    @Override
    public List<Relatives> findRelativesByPhoneNumberStartingWith(String phoneNumber)
    {
        return relativesRepository.findRelativesByPhoneNumberStartingWith(phoneNumber);
    }

    @Override
    public List<Relatives> findRelativesByRelationship(String relationship)
    {
        return relativesRepository.findRelativesByRelationship(relationship);
    }

    @Override
    public Optional<Relatives> findRelativesByEmployeeEmployeeId(Integer employeeId)
    {
        return relativesRepository.findRelativesByEmployeeEmployeeId(employeeId);
    }

    @Override
    public List<Relatives> findRelativesByEmployeeFirstName(String employeeName)
    {
        return relativesRepository.findRelativesByEmployeeFirstName(employeeName);
    }

    @Override
    public List<Relatives> findRelativesByPhoneNumberContaining(String phoneNumber)
    {
        return relativesRepository.findRelativesByPhoneNumberContaining(phoneNumber);
    }

    @Override
    public List<Relatives> findRelativesByEmployeeSalaryLessThan(Integer salary)
    {
        return relativesRepository.findRelativesByEmployeeSalaryLessThan(salary);
    }

    @Override
    public List<Relatives> findRelativesByFullNameStartingWithIgnoreCase(String name)
    {
        return relativesRepository.findRelativesByFullNameStartingWithIgnoreCase(name);
    }











    //U - update

    //D - delete
    @Override
    public void deleteRelative(Integer id)
    {
        relativesRepository.deleteById(id);
    }
}
