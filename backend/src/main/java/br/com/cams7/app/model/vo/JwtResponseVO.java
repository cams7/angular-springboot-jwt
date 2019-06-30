/**
 * 
 */
package br.com.cams7.app.model.vo;

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
public class JwtResponseVO {

	private String token;
	private final String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
}
