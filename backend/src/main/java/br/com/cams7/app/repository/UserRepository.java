/**
 * 
 */
package br.com.cams7.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cams7.app.model.UserEntity;

/**
 * @author ceanm
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
	@EntityGraph(value = UserEntity.LOAD_ROLES)
	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
