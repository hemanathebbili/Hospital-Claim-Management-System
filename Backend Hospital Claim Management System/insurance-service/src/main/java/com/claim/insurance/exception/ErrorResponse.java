package com.claim.insurance.exception;

//import lombok.AllArgsConstructor;
//import lombok.Data;

import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ErrorResponse(LocalDateTime timestamp, int status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}
    
    public ErrorResponse() {
    	
    }
}
