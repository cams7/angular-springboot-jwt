/**
 * 
 */
package br.com.cams7.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author cams7
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RuntimeException {

	public InvalidDataException(String message) {
		super(message);
	}

}
