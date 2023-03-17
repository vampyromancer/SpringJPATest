package kz.zaletov.springMVC.util;

import kz.zaletov.springMVC.DAO.BooksDAO;
import kz.zaletov.springMVC.DAO.PersonDAO;
import kz.zaletov.springMVC.models.Book;
import kz.zaletov.springMVC.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BooksDAO booksDAO;
    @Autowired
    public BookValidator(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if(booksDAO.showName(book.getName()).isPresent()&&booksDAO.showAuthor(book.getAuthor()).isPresent()){
            errors.rejectValue("name","", "The book should be unique");
        }
    }
}
