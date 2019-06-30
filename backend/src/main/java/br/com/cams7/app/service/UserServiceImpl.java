/**
 * 
 */
package br.com.cams7.app.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.error.AppResourceNotFoundException;
import br.com.cams7.app.model.RoleEntity;
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

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder encoder;

	@Transactional(readOnly = true)
	@Override
	public UserEntity getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new AppResourceNotFoundException(
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
		user.setId(null);
		Set<RoleEntity> roles = user.getRoles();
		if (roles != null && !roles.isEmpty())
			user.setRoles(user.getRoles().stream().map(role -> {
				return roleService.getRoleByName(role.getName());
			}).collect(Collectors.toSet()));
		user.setEncryptedPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
