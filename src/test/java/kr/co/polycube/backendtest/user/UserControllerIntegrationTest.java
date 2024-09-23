package kr.co.polycube.backendtest.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.user.db.UserRepo;
import kr.co.polycube.backendtest.user.db.Users;
import kr.co.polycube.backendtest.user.dto.GetUserResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "John");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/users", entity, String.class);

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

    @Test
    public void getUser() {
        List<Users> users = userRepo.findAll(PageRequest.of(0, 1))
                .getContent();
        if (users.isEmpty()) {
            System.err.println("users table에 data가 존재하지 않습니다.");
            fail();
        }

        Users user = users.get(0);
        ResponseEntity<GetUserResDTO> entity = restTemplate.getForEntity("/users/" + user.getId(), GetUserResDTO.class);
        GetUserResDTO res = entity.getBody();

        assertEquals(user.getId(), res.getId());
        assertEquals(user.getName(), res.getName());
    }
}