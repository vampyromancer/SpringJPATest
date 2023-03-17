package kz.zaletov.springMVC.DAO;

import kz.zaletov.springMVC.models.Book;
import kz.zaletov.springMVC.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }
    public void addNew(Book book){
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }
    public void update(int id, Book book){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?", book.getName(),
                book.getAuthor(), book.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
    public Optional<Book> showName(String name){
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new BeanPropertyRowMapper<>(Book.class), name).stream().findAny();
    }
    public Optional<Book> showAuthor(String author){
        return jdbcTemplate.query("SELECT * FROM Book WHERE author=?", new BeanPropertyRowMapper<>(Book.class), author).stream().findAny();
    }
    public void setOwner(int id, Book book){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", book.getPerson_id(), id);
    }

    public void unassign(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
    }

    public List<Book> showPersonBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new BeanPropertyRowMapper<>(Book.class),
                id);
    }
    public Optional<Person> showOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN Book ON Person.id=Book.person_id WHERE Book.id=?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }
}
