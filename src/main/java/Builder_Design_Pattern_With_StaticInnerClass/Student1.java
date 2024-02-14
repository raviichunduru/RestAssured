package Builder_Design_Pattern_With_StaticInnerClass;

import Builder_Design_Pattern_With_ExternalBuilderClass.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


    @Getter
    @AllArgsConstructor
    @JsonInclude(value = JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_EMPTY)
    @ToString

    public class Student1
    {
        private int ID;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String grades;

        public static class StudentBuilder1
        {
            private int ID;
            private String firstName;
            private String lastName;
            private String email;
            private String address;
            private String grades;

            public static StudentBuilder1 builder1()
            {
                return new StudentBuilder1();
            }


            public Student1 getStudent1()
            {
                return new Student1(this.ID,this.firstName,this.lastName,this.email,this.address,this.grades);
            }

            public StudentBuilder1 setID(int ID)
            {
                this.ID = ID;
                return this;
            }

            public StudentBuilder1 setFirstName(String firstName) {
                this.firstName = firstName;
                return this;
            }

            public StudentBuilder1 setLastName(String lastName) {
                this.lastName = lastName;
                return this;
            }

            public StudentBuilder1 setEmail(String email) {
                this.email = email;
                return this;
            }

            public StudentBuilder1 setAddress(String address) {
                this.address = address;
                return this;
            }

            public StudentBuilder1 setGrades(String grades) {
                this.grades = grades;
                return this;
            }
        }
    }

