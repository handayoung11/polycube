package kr.co.polycube.backendtest.lotto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LottoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void createLotto() throws Exception {
        mockMvc.perform(post("/lottos"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numbers", hasSize(6)));;
    }
}