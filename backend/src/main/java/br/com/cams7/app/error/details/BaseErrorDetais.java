/**
 * 
 */
package br.com.cams7.app.error.details;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ceanm
 *
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseErrorDetais {

	private String title;
	private int status;
	private String detail;
	private long timestamp;
	private String developerMessage;
}
