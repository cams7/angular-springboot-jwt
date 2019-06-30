/**
 * 
 */
package br.com.cams7.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.error.AppResourceNotFoundException;
import br.com.cams7.app.model.RoleEntity;
import br.com.cams7.app.model.RoleEntity.RoleName;
import br.com.cams7.app.repository.RoleRepository;

/**
 * @author ceanm
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	@Override
	public RoleEntity getRoleByName(RoleName roleName) {
		return roleRepository.findByName(roleName).orElseThrow(
				() -> new AppResourceNotFoundException(String.format("Fail! -> Cause: %s not find.", roleName.name())));
	}

}
