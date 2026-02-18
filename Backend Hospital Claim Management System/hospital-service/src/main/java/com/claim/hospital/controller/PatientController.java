package com.claim.hospital.controller;

import com.claim.hospital.constants.ApiPaths;
import com.claim.hospital.dto.patient.PatientRequestDTO;
import com.claim.hospital.dto.patient.PatientResponseDTO;
import com.claim.hospital.service.PatientService;
import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.PATIENT)
//@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;
    
    public PatientController(PatientService service) {
		super();
		this.service = service;
	}


	// CREATE
    @PostMapping
    public PatientResponseDTO create(@Valid @RequestBody PatientRequestDTO dto) {
        return service.create(dto);
    }


    // GET BY ID
    @GetMapping("/{id}")
    public PatientResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }


    // GET ALL
    @GetMapping
    public List<PatientResponseDTO> getAll() {
        return service.getAll();
    }


    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Patient deleted successfully";
    }
}
