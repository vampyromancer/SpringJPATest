package kz.zaletov.springMVC.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name shouldn not be empty")
    @Size(min=2, max=100, message = "Name should be between 2 and 30 chars")
    private String name;
    @Min(value = 1900, message = "Should be real year 0")
    @Max(value = 2020, message = "Should be less than 2020")
    private int year;
    public Person(){}

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
