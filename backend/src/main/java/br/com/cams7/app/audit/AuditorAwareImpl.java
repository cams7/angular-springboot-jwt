/**
 * 
 */
package br.com.cams7.app.audit;

import static br.com.cams7.app.security.SecurityUtil.getLoggedUser;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import br.com.cams7.app.model.UserEntity;

/**
 * @author ceanm
 *
 */
public class AuditorAwareImpl implements AuditorAware<UserEntity> {

	@Override
	public Optional<UserEntity> getCurrentAuditor() {
		return Optional.of(getLoggedUser());
	}

}
