package quem.me.ajuda.security.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {
	@NotBlank
	private String registration;
	
	@NotBlank
	private String password;
}