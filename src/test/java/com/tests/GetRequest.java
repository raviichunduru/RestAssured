package com.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;

public class GetRequest
{
    @Test
    public void getTest_PathParameter()
    {
        Response response = given()
                .pathParams("id",601)
                .log()
                .all()
                .get("http://localhost:3000/employees/{id}");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @Test
    public void getTest_QueryParameter() throws IOException
    {
        Response response = given()
                .queryParam("id",601)
                //.queryParam("firstname","Kami")
                .log()
                .all()
                .get("http://localhost:3000/employees/");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));

        Files.write(Paths.get(System.getProperty("user.dir") + ("/response.json")),response.asByteArray());
    }
}
