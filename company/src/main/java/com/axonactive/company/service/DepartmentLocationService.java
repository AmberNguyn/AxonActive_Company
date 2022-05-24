package com.axonactive.company.service;

import com.axonactive.company.entity.DepartmentLocation;

import java.util.List;
import java.util.Optional;

public interface DepartmentLocationService {
    List<DepartmentLocation> getAll();

    //C - create
    public void saveDepartmentLocation(DepartmentLocation departmentLocation);

    //R - retrieve
    public Optional<DepartmentLocation> findDepartmentLocationById(Integer id);

    public List<DepartmentLocation> findDepartmentLocationByDepartmentDepartmentId(Integer deptId);







    //U - update

    //D - delete by Id
    public void deleteDepartmentLocation(Integer id);
}
