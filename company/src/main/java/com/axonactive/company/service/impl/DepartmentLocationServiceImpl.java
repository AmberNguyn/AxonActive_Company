package com.axonactive.company.service.impl;

import com.axonactive.company.entity.DepartmentLocation;
import com.axonactive.company.repository.DepartmentLocationRepository;
import com.axonactive.company.service.DepartmentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentLocationServiceImpl implements DepartmentLocationService {
    private final DepartmentLocationRepository departmentLocationRepository;

    @Override
    public List<DepartmentLocation> getAll()
    {
        return departmentLocationRepository.findAll();
    }

    //C - create
    @Override
    public void saveDepartmentLocation(DepartmentLocation departmentLocation)
    {
        departmentLocationRepository.save(departmentLocation);
    }

    //R - retrieve
    @Override
    public Optional<DepartmentLocation> findDepartmentLocationById(Integer id)
    {
        return departmentLocationRepository.findById(id);
    }

    @Override
    public List<DepartmentLocation> findDepartmentLocationByDepartmentDepartmentId(Integer deptId)
    {
        return departmentLocationRepository.findDepartmentLocationByDepartmentDepartmentId(deptId);
    }








    //U - update

    //D - delete
    @Override
    public void deleteDepartmentLocation(Integer id)
    {
        departmentLocationRepository.deleteById(id);
    }
}
