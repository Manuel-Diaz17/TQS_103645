package com.example.helloWorld;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class RestAssuredTest {
    
    String api = "https://jsonplaceholder.typicode.com/todos";

    @Test
    public void testEndpoint()
    {
        given().when().get(api).then().assertThat().statusCode(200);
    }

    @Test
    public void testToDo4(){
        given().when().get(api + "/4").then().assertThat().statusCode(200).and().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void testListAllTodos(){
        given().when().get(api).then().assertThat().statusCode(200).and().body("id", hasItems(198, 199));
    }
}
