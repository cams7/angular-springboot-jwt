/**
 * 
 */
package br.com.cams7.app.error.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author cams7
 *
 */
@Getter
public class ValidationErrorDetails extends BaseErrorDetails {

	private Field[] fields;

	@Builder
	protected ValidationErrorDetails(String error, String message, String path, int status, String timestamp,
			String trace, Field[] fields) {
		super(error, message, path, status, timestamp, trace);
		this.fields = fields;
	}

	@AllArgsConstructor
	@Getter
	public static final class Field {
		private String name;
		private String message;
	}

}
