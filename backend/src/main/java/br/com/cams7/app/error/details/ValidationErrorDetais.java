/**
 * 
 */
package br.com.cams7.app.error.details;

import lombok.Builder;
import lombok.Getter;

/**
 * @author cams7
 *
 */
@Getter
public class ValidationErrorDetais extends BaseErrorDetais {

	private Field[] fields;

	@Builder
	protected ValidationErrorDetais(String title, int status, String detail, long timestamp, String developerMessage,
			Field[] fields) {
		super(title, status, detail, timestamp, developerMessage);
		this.fields = fields;
	}

	public static final class Field {
		private String name;
		private String message;

		public Field(String name, String message) {
			super();
			this.name = name;
			this.message = message;
		}

		public String getName() {
			return name;
		}

		public String getMessage() {
			return message;
		}

	}

}
