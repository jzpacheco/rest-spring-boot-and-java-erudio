package br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.swagger;

import br.com.jzpacheco.restspringbootandjavaerudio.configs.TestConfigs;
import br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.testcontainers.AbstractionIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractionIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
		given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
			.when()
				.get()
			.then()
				.statusCode(200)
			.extract()
				.body()
					.asString();

		assertTrue(content.contains("Swagger UI"));
	}

}
