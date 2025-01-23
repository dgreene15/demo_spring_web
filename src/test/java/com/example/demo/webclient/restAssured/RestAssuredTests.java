package com.example.demo.webclient.restAssured;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Rest Assured
 * - matchesJsonSchema(<file_path>): Validates response body matches JSON schema in file
 *      - response.then().body(matchesJsonSchema(file)
 */

@Slf4j
public class RestAssuredTests {

    private final static String BASE_URI = "https://jsonplaceholder.typicode.com";
    private record User(Integer userId, Integer id, String title, String body) {}

    @Test
    public void responseAsStringTest() {
        String expectedResponse = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}";

        // Get request
        Response response = given()
                .header("Content-Type", "application/json")
                .get(BASE_URI+ "/posts/1");

        // Test with String Body Compare
        response.then().body("userId", equalTo(1)); //uses Hamcrest matcher
        String responseBody = response.getBody().asString();

        assertThat(responseBody).isEqualTo(expectedResponse);
    }

    @Test
    public void responseAsObjectTest() {

        // Get request
        Response response = given()
                .header("Content-Type", "application/json")
                .get(BASE_URI + "/posts/1");

        // Test with Object Mapping
        User user = response.then().extract().body().as(User.class);
        User expectedUser = new User(1,1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        assertThat(user).isEqualTo(expectedUser);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
