package com.tests;

import com.github.javafaker.Faker;
import com.sun.net.httpserver.Request;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;

public class PostRequest
{
    // Method 1 : Posting request body as a String  -- not recommended for larger Json
    @Test
    public void postTest()
    {
        String request = "{\n" +
                "\"id\": \"105\",\n" +
                "\"first_name\": \"abcd\",\n" +
                "\"last_name\": \"efgh\",\n" +
                "\"email\": \"abc@gmail.com\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .body(request)
                .log().all()
                .post("http://localhost:3000/employees");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));
   }

   // Method 2 : Posting request from an external file
   // printing request content is not possible as it is present in file
   // cant dynamically change file content

   @Test
   public void test1 ()
   {
       Response response = given()
               .header("Content-Type", ContentType.JSON)
               .log()
               .all()
               .body(new File(System.getProperty("user.dir") + "/test.json"))
               .post("http://localhost:3000/employees");

       response.prettyPrint();
       System.out.println("Response Code = " + response.getStatusCode());
       System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));
   }


    //read request body from file and convert to string
    // dynamically changing request content is possible
    // not suitable for large requests, too many fields to change

    @Test
    public void test2 () throws IOException
    {
        String request = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/test.json")));
        String updatedRequest = request.replace("115",String.valueOf(new Faker().number().numberBetween(100,1000)))
                .replace("abcd", new Faker().name().firstName())
                .replace("efgh", new Faker().name().lastName());

        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(updatedRequest)
                .post("http://localhost:3000/employees");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    // using HashMap
    // verbose and hard to build if json is big

    @Test
    public void test3 () throws IOException
    {
        Map<String,Object> request = new LinkedHashMap<>();

        request.put("id",String.valueOf(new Faker().number().numberBetween(100,1000)));
        request.put("firstname",new Faker().name().firstName());
        request.put("lastname",new Faker().name().lastName());
        request.put("email",new Faker().name().firstName() + "@gmail.com");
        request.put("jobs",Arrays.asList("tester","developer","manager"));

        Map<String,Object> food = new LinkedHashMap<>();
        food.put("breakfast","idly");
        food.put("lunch","rice");
        food.put("dinner",Arrays.asList("a","b","c"));
        request.put("fav_foods",food);

        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(request)
                .post("http://localhost:3000/employees");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    // using external JSON Library.. this library having collections to solve problem of using map and lists to construct request
    @Test
    public void test4 () throws IOException
    {
        JSONObject request = new JSONObject();

        request.put("id",String.valueOf(new Faker().number().numberBetween(100,1000)));
        request.put("firstname",new Faker().name().firstName());
        request.put("lastname",new Faker().name().lastName());
        request.put("email",new Faker().name().firstName() + "@gmail.com");
        request.accumulate("email", new Faker().name().firstName() + "@gmail.com");

        JSONArray listOfJobs = new JSONArray();
        listOfJobs.put("tester");
        listOfJobs.put("developer");
        listOfJobs.put("manager");

        request.put("jobs", listOfJobs);

        JSONObject food = new JSONObject();
        food.put("breakfast","idly");
        food.put("lunch","rice");
        JSONArray dinner = new JSONArray();
        dinner.put("a");
        dinner.put("b");
        dinner.put("c");
        food.put("dinner",dinner);

        request.put("fav_foods",food);

        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(request.toString())
                .post("http://localhost:3000/employees");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));
    }
}
