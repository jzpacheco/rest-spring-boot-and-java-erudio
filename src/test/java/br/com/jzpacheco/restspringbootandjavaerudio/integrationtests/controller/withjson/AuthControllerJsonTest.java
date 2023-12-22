package br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.controller.withjson;

import br.com.jzpacheco.restspringbootandjavaerudio.configs.TestConfigs;
import br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.testcontainers.AbstractionIntegrationTest;
import br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.vo.AccountCredentialsVO;
import br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.vo.TokenVO;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerJsonTest extends AbstractionIntegrationTest {

    private static TokenVO tokenVO;

    @Test
    @Order(1)
    public void testSignin() throws IOException {

        AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin123");

         tokenVO = given()
                .basePath("/auth/signin")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenVO.class);

         assertNotNull(tokenVO.getAccessToken());
         assertNotNull(tokenVO.getUsername());
         assertNotNull(tokenVO.getRefreshToken());
    }

    @Test
    @Order(2)
    public void testRefresh() throws IOException {

        AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin123");

        var newTokenVo = given()
                .basePath("/auth/refresh")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                    .pathParam("username", tokenVO.getUsername())
                    .header(TestConfigs.HEADER_PARAM_AUTHORIZATION,"Bearer "+ tokenVO.getRefreshToken())
                .when()
                .put("{username}")
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .as(TokenVO.class);

        assertNotNull(newTokenVo.getAccessToken());
        assertNotNull(newTokenVo.getRefreshToken());
    }
}
