package com.example.employeesmanagement.Pogo;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Employees {



    @NotNull(message = "id Cannot be null")
    @Min(value = 2 , message = "Length more than 2")
    private Long id ;

    @NotEmpty(message = "name Cannot be null")
    @Size(min = 4, message = "Length more than 4")
    private String name ;
    @NotNull(message = "age Cannot be null")
//   //@Pattern(regexp = "^(0|[1-9][0-9]*)$")
//    @Positive(message = "must be a number")
//    @Digits(integer = 10,message = "must be number")
    @Min(value=25 ,message = "Length more than 25")
    private  int age;


    @NotEmpty(message = "position Cannot be null")
    @Pattern(regexp = "supervisor|coordinator")
    private String position;


    @Pattern(regexp = ("(false)$"))
    private String onLeave;
@NotNull(message = "Cannot be null")
@Min(value = 2010)
@Max(value = 2023)
//@PastOrPresent
private int employmentYear;


@NotNull(message = "Cannot be null")
private  int annualLeave;




}
