/**
 * 
 */
package br.com.cams7.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ceanm
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppllegalArgumentException extends RuntimeException {

	public AppllegalArgumentException(String message) {
		super(message);
	}

}
