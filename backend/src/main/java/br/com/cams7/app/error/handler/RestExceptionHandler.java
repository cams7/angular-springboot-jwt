/**
 * 
 */
package br.com.cams7.app.error.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cams7.app.error.InvalidDataException;
import br.com.cams7.app.error.ResourceNotFoundException;
import br.com.cams7.app.error.details.ErrorDetails;
import br.com.cams7.app.error.details.ValidationErrorDetais;

/**
 * @author cams7
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ErrorDetails details = ErrorDetails.builder().title("Resource not found").status(HttpStatus.NOT_FOUND.value())
				.detail(exception.getMessage()).timestamp(new Date().getTime())
				.developerMessage(exception.getClass().getName()).build();
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<?> handleInvalidDataException(InvalidDataException exception) {
		ErrorDetails details = ErrorDetails.builder().title("Invalid data").status(HttpStatus.BAD_REQUEST.value())
				.detail(exception.getMessage()).timestamp(new Date().getTime())
				.developerMessage(exception.getClass().getName()).build();
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception) {
		ErrorDetails details = ErrorDetails.builder().title("Resource not found").status(HttpStatus.NOT_FOUND.value())
				.detail(exception.getMessage()).timestamp(new Date().getTime())
				.developerMessage(exception.getClass().getName()).build();
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<ValidationErrorDetais.Field> fields = new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			fields.add(new ValidationErrorDetais.Field(error.getField(), error.getDefaultMessage()));
		});

		ValidationErrorDetais details = ValidationErrorDetais.builder().title("Field validation error")
				.status(status.value()).detail("Field validation error").timestamp(new Date().getTime())
				.developerMessage(exception.getClass().getName())
				.fields(fields.stream().toArray(ValidationErrorDetais.Field[]::new)).build();
		return new ResponseEntity<>(details, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails details = ErrorDetails.builder().title("Internal Exception").status(status.value())
				.detail(exception.getMessage()).timestamp(new Date().getTime())
				.developerMessage(exception.getClass().getName()).build();
		return new ResponseEntity<>(details, headers, status);
	}
}
