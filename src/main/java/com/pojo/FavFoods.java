package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
//@Setter    // To maintain immutability, we don't want setter methods
@AllArgsConstructor
@NoArgsConstructor
// use of below annotation : if user passes null or empty for any property, it will not be sent through request
@JsonInclude(value = JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_EMPTY)

// below annotation will ensure properties of requests are sent in alphabetic order
@JsonPropertyOrder(alphabetic = true)

// below annotation will ensure mentioned property is excluded in request JSON
@JsonIgnoreProperties(value = {"dummy"})

public class FavFoods
{
    private String breakfast;
    private String lunch;
    private List<String> dinner;
}
