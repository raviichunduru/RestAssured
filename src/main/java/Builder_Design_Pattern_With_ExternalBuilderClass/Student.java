package Builder_Design_Pattern_With_ExternalBuilderClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_EMPTY)
@ToString

public class Student
{
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String grades;
}
