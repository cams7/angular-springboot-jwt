/**
 * 
 */
package br.com.cams7.app.error.details;

import lombok.Builder;

/**
 * @author cams7
 *
 */
public class ErrorDetails extends BaseErrorDetails {

	@Builder
	protected ErrorDetails(String error, String message, String path, int status, String timestamp, String trace) {
		super(error, message, path, status, timestamp, trace);
	}

}
