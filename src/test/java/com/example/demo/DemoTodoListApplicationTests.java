package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.Entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "file:src/test/java/resources/application-test.properties")
class DemoTodoListApplicationTests {

	@Autowired
	private WebTestClient webTeste;
		
	@Test
	void testCreateTodoSuccess() {
		var todoTeste = new Todo("test", "Desc teste blablabla blebleble", false,1);
		
		webTeste.post()
			.uri("/todos")
			.bodyValue(todoTeste)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			
			.jsonPath("$[0].nome").isEqualTo(todoTeste.getNome())
			.jsonPath("$[0].descrição").isEqualTo(todoTeste.getDescrição())
			.jsonPath("$[0].prioridade").isEqualTo(todoTeste.getPrioridade())
			.jsonPath("$[0].realizado").isEqualTo(todoTeste.isRealizado())
			;
		
	
	}

	
	@Test
	void testCreateTodoFailure() {
		
		var todoTeste = new Todo("", null, false, 0);
		
		
		webTeste.post()
		.uri("/todos")
		.bodyValue(todoTeste)
		.exchange()
		
		.expectStatus().isBadRequest();
	}
}
