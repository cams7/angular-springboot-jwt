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
public abstract class BaseErrorDetails {

	private String error;
	private String message;
	private String path;
	private int status;	
	private String timestamp;
	private String trace;
}
