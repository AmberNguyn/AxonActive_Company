package com.axonactive.company.service.impl;

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
    //U - update

    //D - delete
    @Override
    public void deleteRelative(Integer id)
    {
        relativesRepository.deleteById(id);
    }
}
