package com.tests;

import com.github.javafaker.Faker;
import com.pojo.Employee;
import com.sun.net.httpserver.Request;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;

public class updateRequest
{
    @Test
    public void updateTest() throws IOException
    {
        JSONObject request = new JSONObject();
        request.put("firstname",new Faker().name().firstName());
        request.put("lastname",new Faker().name().lastName());

        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .pathParams("id",300)
                .log()
                .all()
                .body(request.toMap())
                .put("http://localhost:3000/employees/{id}");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));

        Files.write(Paths.get(System.getProperty("user.dir") + ("/response.json")),response.asByteArray());
    }
}
