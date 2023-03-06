package com.gomilkyway.profile.adari;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.gomilkyway.profile.adari.configs.WebSecurityConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(WebSecurityConfig.class)
public class WebSecurityConfigTest {
	
	@Autowired
	private MockMvc mockMvc;
	

	/**
	 * Test unauthorized
	 * 		/members should return 3xx redirection
	 * 
	 * @return void
	 * 
	 */
	@Test
	public void testUnauthorized() throws Exception {
		this.mockMvc.perform(get("/members")).andExpect(status().is3xxRedirection());
	}

	/**
	 * Test authentication
	 * 		/members should authenticate and return 200
	 * 		with mockuser
	 * 
	 * @return void
	 * 
	 */
	@WithMockUser(value = "mockuser")
	@Test
	public void testAuthentication() throws Exception {
		this.mockMvc.perform(get("/members")).andExpect(status().isOk());
	}

}
