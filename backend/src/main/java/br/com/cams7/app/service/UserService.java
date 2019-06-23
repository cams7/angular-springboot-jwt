/**
 * 
 */
package br.com.cams7.app.service;

import br.com.cams7.app.model.UserEntity;

/**
 * @author ceanm
 *
 */
public interface UserService {
	UserEntity getUserByUsername(String username);

	Boolean existsUserByUsername(String username);

	Boolean existsUserByEmail(String email);
	
	UserEntity createUser(UserEntity user);

}
