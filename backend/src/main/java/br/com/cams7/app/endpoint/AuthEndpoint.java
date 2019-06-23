/**
 * 
 */
package br.com.cams7.app.endpoint;

import static org.springframework.http.HttpStatus.OK;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.app.message.request.LoginForm;
import br.com.cams7.app.message.request.SignUpForm;
import br.com.cams7.app.message.response.JwtResponse;
import br.com.cams7.app.message.response.ResponseMessage;
import br.com.cams7.app.model.RoleEntity;
import br.com.cams7.app.model.RoleEntity.RoleName;
import br.com.cams7.app.model.UserEntity;
import br.com.cams7.app.security.jwt.JwtProvider;
import br.com.cams7.app.service.RoleService;
import br.com.cams7.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author ceanm
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthEndpoint {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JwtProvider jwtProvider;

	@ApiOperation("Busca as informações do usuário logado.")
	@ResponseStatus(value = OK)
	@PostMapping("/signin")
	public JwtResponse authenticateUser(@ApiParam("Login do usuário.") @Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtResponse response = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return response;
	}

	@ApiOperation("Cadastra um novo usuário.")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(
			@ApiParam("Informações do novo usuário.") @Valid @RequestBody SignUpForm signUpRequest) {
		if (userService.existsUserByUsername(signUpRequest.getUsername()))
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);

		if (userService.existsUserByEmail(signUpRequest.getEmail()))
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);

		// Creating user's account
		UserEntity user = new UserEntity();
		user.setName(signUpRequest.getName());
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				RoleEntity adminRole = roleService.getRoleByName(RoleName.ROLE_ADMIN);
				roles.add(adminRole);
				break;
			case "pm":
				RoleEntity pmRole = roleService.getRoleByName(RoleName.ROLE_PM);
				roles.add(pmRole);
				break;
			default:
				RoleEntity userRole = roleService.getRoleByName(RoleName.ROLE_USER);
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		userService.createUser(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

}
