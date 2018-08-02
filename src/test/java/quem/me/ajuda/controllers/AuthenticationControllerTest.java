/**
 * 
 */
package quem.me.ajuda.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.exceptions.FailedAuthenticationException;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.security.model.UserCredentials;
import quem.me.ajuda.services.AuthenticationService;

/**
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationControllerTest {
	private final String ENDPOINT = "/".concat(Endpoints.LOGIN_ENDPOINT);

	private Student testUser;
	private UserCredentials testCredentials;
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private AuthenticationService authService;
	
	@InjectMocks
	private AuthenticationController authController;

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mockMvc = standaloneSetup(authController).build();
	}
	
	@BeforeEach
	void setUp() {
		this.testUser = new Student("user", "pass", "phone", "email", "password");
		this.testCredentials = new UserCredentials(testUser.getRegistration(), testUser.getPassword());
	}

	/**
	 * @throws Exception 
	 */
	@Test
	void testLoginSucessfully() throws Exception {
		when(authService.authenticate(testCredentials))
			.thenReturn(testUser);
		when(authService.tokenFor(testUser))
			.thenReturn(anyString());
		
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(testCredentials)))
				.andExpect(status().isOk());
	}
	
	@Test
	void testLoginSucessFailed() throws Exception {
		when(authService.authenticate(any()))
			.thenThrow(FailedAuthenticationException.class);
		
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(testCredentials)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

}
