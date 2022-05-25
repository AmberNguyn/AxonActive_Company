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

    public List<DepartmentLocation> findDepartmentLocationByLocation(String location);

    public List<DepartmentLocation> findDepartmentLocationByLocationStartingWith(String text);

    public List<DepartmentLocation> findDepartmentLocationByLocationNot(String text);

    public List<DepartmentLocation> findDepartmentLocationByLocationOrderByDepartmentDepartmentIdDesc(String location);






    //U - update

    //D - delete by Id
    public void deleteDepartmentLocation(Integer id);
}
