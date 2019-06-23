/**
 * 
 */
package br.com.cams7.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cams7.app.model.RoleEntity;
import br.com.cams7.app.model.RoleEntity.RoleName;

/**
 * @author ceanm
 *
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryCustom {
	Optional<RoleEntity> findByName(RoleName roleName);
}
