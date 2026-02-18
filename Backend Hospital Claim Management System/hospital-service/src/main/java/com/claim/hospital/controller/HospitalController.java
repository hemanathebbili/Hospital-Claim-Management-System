package com.claim.hospital.controller;

import com.claim.hospital.entity.Hospital;
import com.claim.hospital.entity.Treatment;
import com.claim.hospital.repository.HospitalRepository;
import com.claim.hospital.repository.TreatmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    private final HospitalRepository hospitalRepo;
    private final TreatmentRepository treatmentRepo;

    public HospitalController(HospitalRepository hospitalRepo,
                              TreatmentRepository treatmentRepo) {
        this.hospitalRepo = hospitalRepo;
        this.treatmentRepo = treatmentRepo;
    }

    // Create Hospital
    @PostMapping("/create")
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalRepo.save(hospital);
    }

    // Get All Hospitals
    @GetMapping("/all")
    public List<Hospital> getAllHospitals() {
        return hospitalRepo.findAll();
    }

    // Create Treatment
    @PostMapping("/treatment/create")
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentRepo.save(treatment);
    }

    // Get All Treatments
    @GetMapping("/treatment/all")
    public List<Treatment> getAllTreatments() {
        return treatmentRepo.findAll();
    }
}
