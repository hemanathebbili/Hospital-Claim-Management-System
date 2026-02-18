package com.claim.hospital.dto.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import lombok.Data;

//@Data
public class PatientRequestDTO {

    @NotBlank(message = "Patient name required")
    private String name;

    @NotNull(message = "Age required")
    private Integer age;

    private String disease;

    @NotNull(message = "Hospital Id required")
    private Long hospitalId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public PatientRequestDTO(@NotBlank(message = "Patient name required") String name,
			@NotNull(message = "Age required") Integer age, String disease,
			@NotNull(message = "Hospital Id required") Long hospitalId) {
		super();
		this.name = name;
		this.age = age;
		this.disease = disease;
		this.hospitalId = hospitalId;
	}
    
    public PatientRequestDTO() {
    	
    }
}
