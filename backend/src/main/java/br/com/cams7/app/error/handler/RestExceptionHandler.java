/**
 * 
 */
package br.com.cams7.app.error.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cams7.app.error.AppInvalidDataException;
import br.com.cams7.app.error.AppResourceNotFoundException;
import br.com.cams7.app.error.AppllegalArgumentException;
import br.com.cams7.app.error.details.ErrorDetails;
import br.com.cams7.app.error.details.ValidationErrorDetails;

/**
 * @author cams7
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AppResourceNotFoundException.class)
	public ResponseEntity<?> handleException(AppResourceNotFoundException exception) {
		ErrorDetails details = ErrorDetails.builder().error("Resource not found").message(exception.getMessage())
				.path("").status(HttpStatus.NOT_FOUND.value()).timestamp(timestamp())
				.trace(exception.getClass().getName()).build();
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AppInvalidDataException.class)
	public ResponseEntity<?> handleException(AppInvalidDataException exception) {
		ErrorDetails details = ErrorDetails.builder().error("Invalid data").message(exception.getMessage()).path("")
				.status(HttpStatus.BAD_REQUEST.value()).timestamp(timestamp()).trace(exception.getClass().getName())
				.build();
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AppllegalArgumentException.class)
	public ResponseEntity<?> handleException(AppllegalArgumentException exception) {
		ErrorDetails details = ErrorDetails.builder().error("Ilegal argument").message(exception.getMessage()).path("")
				.status(HttpStatus.BAD_REQUEST.value()).timestamp(timestamp()).trace(exception.getClass().getName())
				.build();
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<ValidationErrorDetails.Field> fields = new ArrayList<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			fields.add(new ValidationErrorDetails.Field(error.getField(), error.getDefaultMessage()));
		});

		ValidationErrorDetails details = ValidationErrorDetails.builder().error("Field validation error")
				.message("Field validation error").path(request.getContextPath()).status(status.value())
				.timestamp(timestamp()).trace(exception.getClass().getName())
				.fields(fields.stream().toArray(ValidationErrorDetails.Field[]::new)).build();
		return new ResponseEntity<>(details, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails details = ErrorDetails.builder().error("Internal Exception").message(exception.getMessage())
				.path(request.getContextPath()).status(status.value()).timestamp(timestamp())
				.trace(exception.getClass().getName()).build();
		return new ResponseEntity<>(details, headers, status);
	}

	private static String timestamp() {
		// 2019-06-30T21:16:52.342+0000
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}
}
