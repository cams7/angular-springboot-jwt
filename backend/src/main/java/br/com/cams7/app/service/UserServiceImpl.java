/**
 * 
 */
package br.com.cams7.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.error.ResourceNotFoundException;
import br.com.cams7.app.model.UserEntity;
import br.com.cams7.app.repository.UserRepository;

/**
 * @author ceanm
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserEntity getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(
				String.format("User Not Found with -> username or email : %s", username)));
	}

	@Transactional(readOnly = true)
	@Override
	public Boolean existsUserByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public Boolean existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}

}
