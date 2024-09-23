package kr.co.polycube.backendtest.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.user.db.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepo userRepo;

    @Test
    public void createUser() {
        // Set up request headers and body
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "John");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // Perform the HTTP POST request
        ResponseEntity<String> response = restTemplate.postForEntity("/users", entity, String.class);

        // Validate the response
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        Long id = null;
        try {
            JsonNode node = objectMapper.readTree(response.getBody());
            id = node.get("id").asLong();
        } catch (Exception e) {
            fail();
        }


        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("user 삭제 실패");
            e.printStackTrace();
        }
    }
}