package Builder_Design_Pattern_With_BuilderAnnotation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


    @Getter
    @AllArgsConstructor
    @JsonInclude(value = JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_EMPTY)
    @ToString
    @Builder(setterPrefix = "set", buildMethodName = "getStudent2")
    public class Student2
    {
        private int ID;
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String grades;
    }

