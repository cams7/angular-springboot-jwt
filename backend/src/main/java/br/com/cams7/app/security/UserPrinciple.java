/**
 * 
 */
package br.com.cams7.app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Builder;

/**
 * @author ceanm
 *
 */
@Getter
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@SuppressWarnings("serial")
public class UserPrinciple implements UserDetails {

	private Long id;

	private String name;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	private final boolean accountNonExpired = true;
	private final boolean accountNonLocked = true;
	private final boolean credentialsNonExpired = true;
	private final boolean enabled = true;

}
