/**
 * 
 */
package br.com.cams7.app.model.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ceanm
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthLoginInfoVO {
	@NotBlank
    @Size(min=3, max = 60)
    private String username;

    @NotBlank
    @Size(min = 3, max = 40)
    private String password;

}
