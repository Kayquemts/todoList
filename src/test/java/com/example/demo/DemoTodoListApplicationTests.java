package com.example.demo;

import com.example.demo.Entity.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class DemoTodoListApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private final Todo todoTeste = new Todo(
			null,
			"Estudar Java",
			"Estudar Spring Boot e JUnit",
			true,
			1
	);

	@Test
	void testCreateTodoSuccess() throws Exception {

		mockMvc.perform(post("/todos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(todoTeste)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].nome").value(todoTeste.getNome()))
				.andExpect(jsonPath("$[0].descricao").value(todoTeste.getDescricao()))
				.andExpect(jsonPath("$[0].prioridade").value(todoTeste.getPrioridade()))
				.andExpect(jsonPath("$[0].realizado").value(todoTeste.isRealizado()));
	}
}