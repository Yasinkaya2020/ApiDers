package com.techproed;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {
    /*
	 Positive Scenario:
	 When Asagidaki Endpoint'e bir GET request yolladim
	 https://restful-booker.herokuapp.com/booking/7
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Sally"
     And lastname "Jackson"
     And checkin date "2017-04-19"
     And checkout date "2020-03-22"
	*/
    @Test
    public void get01(){
        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response=given().accept("application/json").when().get(url);
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);


        response.then().
                assertThat().
                body("firstname", equalTo("Jim")).
                body("lastname", equalTo("Brown")).
                body("totalprice", equalTo(134)).
                body("depositpaid", equalTo(true)).
                body("bookingdates.checkin", equalTo("2021-01-25")).
                body("bookingdates.checkout", equalTo("2021-01-26"));
        //    body("additionalneeds",Matchers.equalTo("Breakfast"));

        //2.Yol
        //Tekrarli body lerden kurtulmak istiyorum
        response.
                then().
                assertThat().body("firstnam",equalTo("Jim"),
                "lastname", equalTo("Brown"),
                "totalprice", equalTo(134),
                "depositpaid", equalTo(true),
                "bookingdates.checkin", equalTo("2021-01-25"),
                "bookingdates.checkout", equalTo("2020-09-24"));

    }
}
