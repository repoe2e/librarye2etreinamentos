package br.com.e2etreinamentos.library_e2e.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorMessage {
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime timestamp;
	    private int status;
	    private String error;
	    private String message;
	    private String path;

	    public ErrorMessage(HttpStatus status, String message, String path) {
	        this.timestamp = LocalDateTime.now();
	        this.status = status.value();
	        this.error = status.getReasonPhrase();
	        this.message = message;
	        this.path = path;
	    }

}
