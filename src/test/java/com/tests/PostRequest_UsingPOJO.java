package com.tests;

import com.pojo.Employee;
import com.pojo.FavFoods;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;

public class PostRequest_UsingPOJO
{
    // plan old java object
    // {} --> class file
    // [] --> List<type>


    @Test
    public void POJOTest() throws IOException
    {
        List<String> dinner = Arrays.asList("chapathi","milk");
        List<String> listOfJobs = Arrays.asList("Dev","Test");
        FavFoods favFoods = new FavFoods("idly","rice", dinner);
        Employee employee = new Employee("601","fn1","ln1","email1@gmail.com", favFoods, listOfJobs);

        Response response = given()
                .header("Content-Type", ContentType.JSON)  // expecting response in JSON format
                .log()
                .all()
                .body(employee)  // RestAssured internally uses a serializer to convert the object into JSON format.
                .post("http://localhost:3000/employees");

        response.prettyPrint();
        System.out.println("Response Code = " + response.getStatusCode());
        System.out.println("Response Time in MilliSeconds = " + response.getTimeIn(TimeUnit.MILLISECONDS));

        Assert.assertEquals(response.statusCode(),201);
        Assert.assertEquals(response.contentType(),"application/json");

        // validating response fields
        Employee deserializedEmployee = response.as(Employee.class); // JSON --> Class

        Assert.assertEquals(deserializedEmployee.getId(),"601");
        //Assert.assertEquals(deserializedEmployee.getId(),employee.getId());
        Assert.assertEquals(deserializedEmployee.getEmail(),"email1@gmail.com");
        Assert.assertEquals(deserializedEmployee.getListOfJobs(), Arrays.asList("Dev", "Test"));


        //validating response schema
        response.then().body(JsonSchemaValidator.matchesJsonSchema(
                new File(System.getProperty("user.dir")+"/src/test/resources/json_schema/schema.json")));

        Files.write(Paths.get(System.getProperty("user.dir") + ("/response.json")),response.asByteArray());
    }
}
