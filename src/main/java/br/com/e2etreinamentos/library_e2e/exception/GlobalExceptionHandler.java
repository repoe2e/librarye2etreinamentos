package br.com.e2etreinamentos.library_e2e.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.e2etreinamentos.library_e2e.exception.CadastroLivroException.LivroJaCadastradoException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(LivroJaCadastradoException.class)
	    public ResponseEntity<Object> handleLivroJaCadastradoException(LivroJaCadastradoException ex, WebRequest request) {
	        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT, ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
