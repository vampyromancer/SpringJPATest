package kz.zaletov.springMVC.DAO;

import kz.zaletov.springMVC.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Optional<Person> showYear(int year){
        return jdbcTemplate.query("SELECT * FROM Person WHERE year=?", new BeanPropertyRowMapper<>(Person.class), year)
                .stream().findAny();
    }
    public Optional<Person> showName(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new BeanPropertyRowMapper<>(Person.class), name)
                .stream().findAny();
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }
    public void addNew(Person person){
        jdbcTemplate.update("INSERT INTO Person (name, year) VALUES(?, ?)",
                person.getName(), person.getYear());
    }
    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
