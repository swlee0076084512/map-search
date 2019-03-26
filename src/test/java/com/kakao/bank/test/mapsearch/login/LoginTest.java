package com.kakao.bank.test.mapsearch.login;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void loginTest() throws JSONException {

        JSONObject json = new JSONObject();
        json.put("username","test");
        json.put("password","1234");
        json.put("role","USER");


        webTestClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(json.toString())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }

    @Test
    public void loginFailTest() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("username","test");
        json.put("password","1111");
        json.put("role","USER");


        webTestClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(json.toString())
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}
