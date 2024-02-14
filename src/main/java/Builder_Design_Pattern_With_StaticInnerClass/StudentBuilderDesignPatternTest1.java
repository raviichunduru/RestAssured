package Builder_Design_Pattern_With_StaticInnerClass;

import Builder_Design_Pattern_With_ExternalBuilderClass.Student;
import org.testng.annotations.Test;

import static Builder_Design_Pattern_With_ExternalBuilderClass.StudentBuilder.builder;

public class StudentBuilderDesignPatternTest1
{
    @Test
    public void StudentBuilderDesignPatternTest1()
    {
        Student1 student1 = Student1.StudentBuilder1.builder1().setID(2).setFirstName("").setEmail("a@b.com").setAddress("address").getStudent1();
        System.out.println(student1);
    }
}
