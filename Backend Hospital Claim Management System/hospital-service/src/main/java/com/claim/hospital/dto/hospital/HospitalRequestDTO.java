package com.claim.hospital.dto.hospital;

import jakarta.validation.constraints.NotBlank;
//import lombok.Data;

//@Data
public class HospitalRequestDTO {

    @NotBlank(message = "Hospital name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Contact number is required")
    private String contact;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public HospitalRequestDTO(@NotBlank(message = "Hospital name is required") String name,
			@NotBlank(message = "Address is required") String address,
			@NotBlank(message = "Contact number is required") String contact) {
		super();
		this.name = name;
		this.address = address;
		this.contact = contact;
	}
    
    public HospitalRequestDTO() {
    	
    }
}
