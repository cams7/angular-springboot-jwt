/**
 * 
 */
package br.com.cams7.app.endpoint;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ceanm
 *
 */
@Api("Endpoint utilizado para teste de acesso por função (PERFIL).")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(produces = TEXT_PLAIN_VALUE)
public class TestEndpoint {

	@ApiOperation("Acesso exclusivo pelas funçoes (ROLES): USER e ADMIN.")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@ResponseStatus(OK)
	@GetMapping("/api/test/user")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@ApiOperation("Acesso exclusivo pelas funções (ROLES): PM e ADMIN.")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	@ResponseStatus(OK)
	@GetMapping("/api/test/pm")
	public String projectManagementAccess() {
		return ">>> Project Management Board";
	}

	@ApiOperation("Acesso exclusivo pelas função (ROLE): ADMIN.")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(OK)
	@GetMapping("/api/test/admin")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
}
