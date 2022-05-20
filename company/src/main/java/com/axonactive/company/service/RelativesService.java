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

    //U - update


    //D - delete
    public void deleteRelative(Integer id);
}
