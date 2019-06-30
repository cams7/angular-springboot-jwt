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
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppResourceNotFoundException extends RuntimeException {

	public AppResourceNotFoundException(String message) {
		super(message);
	}

}
