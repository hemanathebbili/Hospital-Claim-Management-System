package com.claim.hospital.dto.patient;

//import lombok.Data;

//@Data
public class PatientResponseDTO {

    private Long id;

    private String name;

    private Integer age;

    private String disease;

    private Long hospitalId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public PatientResponseDTO(Long id, String name, Integer age, String disease, Long hospitalId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.disease = disease;
		this.hospitalId = hospitalId;
	}
    
    public PatientResponseDTO() {
    	
    }
}
