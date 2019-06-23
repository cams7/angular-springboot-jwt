/**
 * 
 */
package br.com.cams7.app.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ceanm
 *
 */
@Getter
@AllArgsConstructor
public class JwtResponse {

	private String token;
	private final String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
}
