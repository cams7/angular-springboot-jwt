/**
 * 
 */
package br.com.cams7.app.service;

import br.com.cams7.app.model.RoleEntity;
import br.com.cams7.app.model.RoleEntity.RoleName;

/**
 * @author ceanm
 *
 */
public interface RoleService {
	RoleEntity getRoleByName(RoleName roleName);
}
