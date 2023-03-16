package com.katabe.crudspringboot;


import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @SpringBootTest
    public class ControllerIntegrationTest {
        @Test
        @Order(1)
        void getAllConutriesTests() throws JSONException {
            String expected ="[\n" +
                    "    {\n" +
                    "        \"id\": 1,\n" +
                    "        \"countryName\": \"India\",\n" +
                    "        \"countryCapital\": \"Delhi\"\n" +
                    "    },\r\n" +
                    "    {\n" +
                    "        \"id\": 2,\n" +
                    "        \"countryName\": \"USA\",\n" +
                    "        \"countryCapital\": \"Washington\"\n" +
                    "    },\r\n" +
                    "    {\n" +
                    "        \"id\": 3,\n" +
                    "        \"countryName\": \"DRC\",\n" +
                    "        \"countryCapital\": \"Kinshasa\"\n" +
                    "    },\r\n" +
                    "    {\n" +
                    "        \"id\": 5,\n" +
                    "        \"countryName\": \"RSA\",\n" +
                    "        \"countryCapital\": \"Pretoria\"\n" +
                    "    }\n" +
                    "]";
            TestRestTemplate testRestTemplate = new TestRestTemplate();
            ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getcountries",String.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            JSONAssert.assertEquals(expected, response.getBody(), false);
        }
    }
