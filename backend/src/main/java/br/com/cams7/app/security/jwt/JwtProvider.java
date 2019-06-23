/**
 * 
 */
package br.com.cams7.app.security.jwt;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.cams7.app.security.UserPrinciple;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ceanm
 *
 */
@Slf4j
@Component
public class JwtProvider {

	@Value("${grokonez.app.jwtSecret}")
	private String jwtSecret;

	@Value("${grokonez.app.jwtExpiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

		// @formatter:off
		final String token = Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
				.signWith(HS512, jwtSecret)
				.compact();
		// @formatter:on
		return token;
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty -> Message: {}", e);
		}

		return false;
	}

	public String getUserNameFromJwtToken(String token) {
		final String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return username;
	}

}
