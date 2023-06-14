package br.com.springboot.springsecurity.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecurityConfigTests {

  private TestRestTemplate restTemplate;
  private URL base;

  @LocalServerPort
  int port;

  @Before
  public void setUp() throws MalformedURLException {
    this.restTemplate = new TestRestTemplate("admin", "password");
    this.base = new URL("http://localhost:" + port + "/users");
  }

  @Test
  public void whenLoggedUserRequestsHomePage_ThenSuccess()
      throws IllegalStateException, IOException {
    ResponseEntity<String> response = restTemplate.getForEntity(base.toString(),
        String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void whenUserWithWrongCredentials_thenUnauthorizedPage()
      throws Exception {

    restTemplate = new TestRestTemplate("admin", "wrongpassword");
    ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertTrue(response.getBody() == null);
  }

}
