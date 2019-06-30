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
public class AppInvalidDataException extends RuntimeException {

	public AppInvalidDataException(String message) {
		super(message);
	}

}
