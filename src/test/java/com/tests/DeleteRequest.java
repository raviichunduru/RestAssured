package com.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class DeleteRequest
{
    @Test
    public void deleteTest() throws IOException
    {
        Response response  = given()
                .log()
                .all()
                .pathParams("id",300)
                .delete("http://localhost:3000/employees/{id}");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));

        Files.write(Paths.get(System.getProperty("user.dir") + ("/response.json")),response.asByteArray());
    }
}
