package com.example.hg;

import com.example.hg.model.user.UserCreateRequestDto;
import com.example.hg.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        userService.createUser(UserCreateRequestDto.builder().userName("_testuser1").userEmail("_test1@gmail.com").build());
        userService.createUser(UserCreateRequestDto.builder().userName("_testuser2").userEmail("_test2@gmail.com").build());
        userService.createUser(UserCreateRequestDto.builder().userName("_testuser3").userEmail("_test3@gmail.com").build());
        userService.createUser(UserCreateRequestDto.builder().userName("_testuser4").userEmail("_test4@gmail.com").build());
        userService.createUser(UserCreateRequestDto.builder().userName("_testuser5").userEmail("_test5@gmail.com").build());
    }


    @Test
    public void getUsersTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createUserTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserCreateRequestDto req = new UserCreateRequestDto();
        req.setUserName("username");
        req.setUserEmail("_test@gmail.com");
        String json = mapper.writeValueAsString(req);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/users")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
}
