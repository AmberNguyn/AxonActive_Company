package com.axonactive.company.api;

import com.axonactive.company.entity.Relatives;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.repository.RelativesRepository;
import com.axonactive.company.service.RelativesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(RelativesRestController.PATH)
public class RelativesRestController {
    private final RelativesService relativesService;
    private final RelativesRepository relativesRepository;
    public static final String PATH = "api/relatives";

    @GetMapping()
    public List<Relatives> getAllRelatives() {
        List<Relatives> allRelatives = relativesService.getAll();
        return allRelatives;
    }

    //C - created
    @PostMapping("/add")
    public void addRelative(@RequestBody Relatives relatives) {
        relativesService.saveRelatives(relatives);
        System.out.println("Successfully added");
    }

    //R - retrieve
    @GetMapping("/{id}")
    public ResponseEntity<Relatives> getRelativeById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Relatives relatives = relativesService.findRelativeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Relative not found: " + id)
                );
        return ResponseEntity.ok().body(relatives);
    }
    //U - update
    @PutMapping("/{id}")
    public ResponseEntity<Relatives> updateRelatives(@RequestBody Relatives newRelatives, @PathVariable(value = "id") Integer id) throws ResourceNotFoundException
    {
        Relatives relatives = relativesService.findRelativeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Id " + id + " not found" )
                );
        relatives.setRelationship(newRelatives.getRelationship());
        relatives.setFullName(newRelatives.getFullName());
        relatives.setPhoneNumber(newRelatives.getPhoneNumber());
        relatives.setGender(newRelatives.getGender());
        Relatives updatedRelative = relativesRepository.save(relatives);

        return ResponseEntity.ok(updatedRelative);
    }



    //D - delete
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRelativeById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Relatives relatives = relativesService.findRelativeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Relative not found: " + id)
                );
        relativesService.deleteRelative(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
