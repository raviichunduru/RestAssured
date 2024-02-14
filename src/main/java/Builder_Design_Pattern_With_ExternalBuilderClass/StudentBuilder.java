package Builder_Design_Pattern_With_ExternalBuilderClass;


public class StudentBuilder
{
    private StudentBuilder() {};

    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String grades;

   public static StudentBuilder builder()
   {
       return new StudentBuilder();
   }

   public Student getStudent()
   {
       return new Student(this.ID,this.firstName,this.lastName,this.email,this.address,this.grades);
   }

    public StudentBuilder setID(int ID) {
        this.ID = ID;
        return this;
    }

    public StudentBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public StudentBuilder setGrades(String grades) {
        this.grades = grades;
        return this;
    }
}
