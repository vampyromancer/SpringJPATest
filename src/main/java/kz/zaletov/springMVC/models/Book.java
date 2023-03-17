package kz.zaletov.springMVC.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book{
    private int id;
    @NotEmpty(message = "Book name shouldn't be empty")
    @Size(min=1, max=100, message = "Book name should be between 1 and 100 chars")
    private String name;
    @NotEmpty(message = "Author name shouldn't be empty")
    @Size(min=2, max=100, message = "Author name should be between 2 and 100 chars")
    private String author;
    @Min(value = 1000, message = "More than 1000")
    @Max(value = 2022, message = "Less than 2022")
    private int year;
    private Integer person_id;

    public Book() {
    }
    public Book(int id, String name, String author, int year) {
        this.id=id;
        this.name = name;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
