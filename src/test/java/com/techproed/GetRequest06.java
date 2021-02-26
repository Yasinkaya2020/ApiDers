package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest06 extends TestBaseJsonPlaceHolder {
    /*
     1)When I send a GET request to REST API URL
						 https://jsonplaceholder.typicode.com/todos/123
					     Then HTTP Status Code should be 200
					     And "Server" in Headers should be "cloudflare"
					     And response content type is “application/JSON”
					     And "userId" should be 7,
					     And "title" should be "esse et quis iste est earum aut impedit"
						 And "completed" should be false

     */

    @Test
    public void get01(){
        //Url olustur
        spec01.pathParams("name","todos","id",123);
        //datayi olustur---Sonra
        //request gonder

        //test body
        Response response=given().
                spec(spec01).
                when().get("/{name}/{id}");
        //Assert yap
        response.prettyPrint();
        response.then().
                assertThat().
                statusCode(200).
                header("Server","cloudflare");
        response.then().
                assertThat().
                contentType("application/JSON").
                body("userId", equalTo(7),
                        "title",
                        equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));


    }
}
