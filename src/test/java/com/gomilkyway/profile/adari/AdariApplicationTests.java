package com.gomilkyway.profile.adari;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdariApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 * Test the status of the application
	 * 		/status should return OK as text
	 * 
	 * @return void
	 * 
	 */
	@Test
	public void getStatus() throws Exception {
		ResponseEntity<String> body = this.testRestTemplate.getForEntity("/status", String.class);
		assertThat(body.getBody()).isEqualTo("OK");
	}

}
