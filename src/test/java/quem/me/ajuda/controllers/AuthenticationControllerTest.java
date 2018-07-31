/**
 * 
 */
package quem.me.ajuda.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import quem.me.ajuda.constants.Endpoints;
import quem.me.ajuda.models.Student;
import quem.me.ajuda.services.StudentService;

/**
 *
 */
@SpringBootTest
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationControllerTest {
	private final String ENDPOINT = "/".concat(Endpoints.LOGIN_ENDPOINT);

	private Student testUser;
	
	private MockMvc mockMvc;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mockMvc = standaloneSetup(AuthenticationController.class).build();
	}
	
	@BeforeEach
	void setUp() {
		testUser = new Student("user", "pass", "phone", "email", "password");
		this.studentService.create(testUser);
	}

	/**
	 * @throws Exception 
	 */
	@Test
	void testLoginSucessfully() throws Exception {
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testUser)))
				.andExpect(status().isOk());
	}

}
