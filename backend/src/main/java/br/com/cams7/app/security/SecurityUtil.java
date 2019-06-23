/**
 * 
 */
package br.com.cams7.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.cams7.app.model.UserEntity;

/**
 * @author ceanm
 *
 */
public final class SecurityUtil {
	public static final UserEntity getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			return null;
		
		UserEntity user = new UserEntity();
		user.setId(1l);
		return user;
		
//		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
//
//		UserEntity user = new UserEntity();
//		user.setId(userPrinciple.getId());
//		user.setName(userPrinciple.getName());
//		user.setUsername(userPrinciple.getUsername());
//		user.setEmail(userPrinciple.getEmail());
//		user.setPassword(userPrinciple.getPassword());
//
//		return user;
		
		
	}

}
