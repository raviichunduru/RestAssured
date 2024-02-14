package Builder_Design_Pattern_With_BuilderAnnotation;

import org.testng.annotations.Test;

public class StudentBuilderDesignPatternTest2
{
    @Test
    public void StudentBuilderDesignPatternTest2()
    {
        Student2 student2 = Student2.builder().setID(3).setFirstName("").setEmail("a@b.com").setAddress("address").getStudent2();
        System.out.println(student2);
    }
}
