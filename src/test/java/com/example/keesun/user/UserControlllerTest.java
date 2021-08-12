package com.example.keesun.user;

import com.example.keesun.KeesunApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControlllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void hello() throws Exception {
		mockMvc.perform(get("/hello"))
			   .andExpect(status().isOk())
			   .andExpect(content().string("hello"));
	}

	@Test
	public void createUser_JSON() {
		String userJson = "";
		mockMvc.perform(post("/users/create")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON)
				.content(userJson))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.userName", is(equalTo("keesun"))))
			   .andExpect(jsonPath("$.password", is(equalTo("123"))));
	}
}
