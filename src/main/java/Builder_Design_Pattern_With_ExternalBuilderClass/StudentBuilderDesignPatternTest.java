package Builder_Design_Pattern_With_ExternalBuilderClass;

import org.testng.annotations.Test;

import static Builder_Design_Pattern_With_ExternalBuilderClass.StudentBuilder.builder;

public class StudentBuilderDesignPatternTest
{
    @Test
    public void StudentBuilderDesignPatternTest()
    {
        Student student = builder().setID(1).setFirstName("").setEmail("a@b.com").setAddress("address").getStudent();
        System.out.println(student);
    }

}
