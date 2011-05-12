package quem.me.ajuda.security.model;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import quem.me.ajuda.models.Student;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 1L;
	private String token;
	private Student user;
	
	public AuthenticatedUser(Student user) {
		this.user = user;
	}
	
	public AuthenticatedUser(String token) {
		this.token = token;
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public Object getCredentials() {
		return this.token;
	}
	
	@Override
	public Object getDetails() {
		return null;
	}
	
	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAuthenticated() {
		return this.user != null;
	}
	
	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		
	}
}
