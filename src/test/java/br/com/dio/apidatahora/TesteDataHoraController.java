package br.com.dio.apidatahora;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TesteDataHoraController {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void deveRetornarDataHoraComTiezoneDefault() {
        var metodo = "GET";
        var endpoint = "/data-hora";

        var response = when()
                .request(metodo, endpoint)
                .then()
                .extract()
                .response();

        var json = response.jsonPath();

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(json.get("dataHora"));
    }
}
