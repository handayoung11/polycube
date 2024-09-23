package kr.co.polycube.backendtest.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.user.db.UserRepo;
import kr.co.polycube.backendtest.user.db.Users;
import kr.co.polycube.backendtest.user.dto.GetUserResDTO;
import kr.co.polycube.backendtest.user.dto.PatchUserResDTO;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepo userRepo;

    private ResponseEntity<String> createUserReq() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "John");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/users", entity, String.class);
        return response;
    }

    private Long getId(ResponseEntity<String> response) {
        Long id = null;
        try {
            JsonNode node = objectMapper.readTree(response.getBody());
            id = node.get("id").asLong();
        } catch (Exception e) {
            fail();
        }
        return id;
    }

    private void deleteUser(Long id) {
        try {
            userRepo.deleteById(id);
        } catch (Exception e) {
            System.err.println("user 삭제 실패");
            e.printStackTrace();
        }
    }

    @Test
    public void createUser() {
        ResponseEntity<String> response = createUserReq();
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        Long id = getId(response);

        deleteUser(id);
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

    @Test
    public void updateUser() {
        ResponseEntity<String> response = createUserReq();
        Long id = getId(response);
        String name = "changed_name";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", name);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        PatchUserResDTO dto = restTemplate.patchForObject("/users/" + id, entity, PatchUserResDTO.class);
        assertEquals(dto.getId(), id);
        assertEquals(dto.getName(), name);

        deleteUser(id);
    }

    @Test
    public void urlValidatorFilterTest() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/users/1?name=test!!", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(entity.getBody());
    }
}