/**
 * 
 */
package br.com.cams7.app.endpoint;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.app.error.AppllegalArgumentException;
import br.com.cams7.app.model.UserEntity;
import br.com.cams7.app.model.vo.AuthLoginInfoVO;
import br.com.cams7.app.model.vo.JwtResponseVO;
import br.com.cams7.app.security.jwt.JwtProvider;
import br.com.cams7.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author ceanm
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/auth", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
public class AuthEndpoint {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtProvider jwtProvider;

	@ApiOperation("Busca as informações do usuário logado.")
	@ResponseStatus(OK)
	@PostMapping("/signin")
	public JwtResponseVO authenticateUser(
			@ApiParam("Login do usuário.") @Valid @RequestBody AuthLoginInfoVO loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtResponseVO response = new JwtResponseVO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return response;
	}

	@ApiOperation("Cadastra um novo usuário.")
	@ResponseStatus(OK)
	@PostMapping("/signup")
	public UserEntity registerUser(@ApiParam("Informações do novo usuário.") @Valid @RequestBody UserEntity user) {
		if (userService.existsUserByUsername(user.getUsername()))
			throw new AppllegalArgumentException("Fail -> Username is already taken!");

		if (userService.existsUserByEmail(user.getEmail()))
			throw new AppllegalArgumentException("Fail -> Email is already in use!");

		userService.createUser(user);
		return user;
	}

}
