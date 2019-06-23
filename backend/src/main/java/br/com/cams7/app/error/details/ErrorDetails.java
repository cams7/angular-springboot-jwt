/**
 * 
 */
package br.com.cams7.app.error.details;

import lombok.Builder;

/**
 * @author cams7
 *
 */
public class ErrorDetails extends BaseErrorDetais {

	@Builder
	protected ErrorDetails(String title, int status, String detail, long timestamp, String developerMessage) {
		super(title, status, detail, timestamp, developerMessage);
	}

}
