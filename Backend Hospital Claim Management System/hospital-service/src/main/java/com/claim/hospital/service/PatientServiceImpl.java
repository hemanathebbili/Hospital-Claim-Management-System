package com.claim.hospital.service;

import com.claim.hospital.dto.patient.PatientRequestDTO;
import com.claim.hospital.dto.patient.PatientResponseDTO;
import com.claim.hospital.entity.Patient;
import com.claim.hospital.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final ModelMapper mapper;

    public PatientServiceImpl(PatientRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PatientResponseDTO create(PatientRequestDTO dto) {
        Patient patient = mapper.map(dto, Patient.class);
        Patient saved = repository.save(patient);
        return mapper.map(saved, PatientResponseDTO.class);
    }

    @Override
    public PatientResponseDTO getById(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return mapper.map(patient, PatientResponseDTO.class);
    }

    @Override
    public List<PatientResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(p -> mapper.map(p, PatientResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
