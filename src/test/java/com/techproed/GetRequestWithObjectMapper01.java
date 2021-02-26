package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import Utilities.JSonUtil;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {
    /*
	 	When
	 		I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198

	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }

     */
    @Test
            public void get01(){

        //Url olustur
        spec01.pathParams("todosPath","todos","id",198);
        //Expected -Beklenen Datayi olustur
        String expectedJson = "{\n" +
                " \"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                " \"completed\": true\n" +
                " }";
        Map<String ,Object> expectedMap= JSonUtil.convertJsonToJava(expectedJson, Map.class);
        System.out.println(expectedMap);

        //Request gonder
        Response response=given().spec(spec01).when().get("/{todosPath}/{id}");

        response.prettyPrint();

        //response bodyi Map e cevirecegiz
        Map<String ,Object> actualDatMap=JSonUtil.convertJsonToJava(response.asString(),Map.class);
        System.out.println(actualDatMap);


        //Assertion
        Assert.assertEquals(expectedMap.get("userId"),actualDatMap.get("userId"));
        Assert.assertEquals(expectedMap.get("completed"),actualDatMap.get("completed"));
        Assert.assertEquals(expectedMap.get("title"),actualDatMap.get("title"));
        Assert.assertEquals(expectedMap.get("id"),actualDatMap.get("id"));


    }

}
