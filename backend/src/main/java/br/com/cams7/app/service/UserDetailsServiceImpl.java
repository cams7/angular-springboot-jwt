/**
 * 
 */
package br.com.cams7.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cams7.app.error.ResourceNotFoundException;
import br.com.cams7.app.model.UserEntity;
import br.com.cams7.app.security.UserPrinciple;

/**
 * @author ceanm
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UserEntity user = userService.getUserByUsername(username);
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

			return UserPrinciple.builder().id(user.getId()).name(user.getName()).username(user.getUsername())
					.email(user.getEmail()).password(user.getPassword()).authorities(authorities).build();
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
