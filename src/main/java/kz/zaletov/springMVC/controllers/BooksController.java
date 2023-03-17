package kz.zaletov.springMVC.controllers;

import jakarta.validation.Valid;
import kz.zaletov.springMVC.DAO.BooksDAO;
import kz.zaletov.springMVC.DAO.PersonDAO;
import kz.zaletov.springMVC.models.Book;
import kz.zaletov.springMVC.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;
    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksDAO.show(id));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("bookOwner", booksDAO.showOwner(id));
        return "books/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book,bindingResult);
        if(bindingResult.hasErrors())
            return "books/new";
        booksDAO.addNew(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        bookValidator.validate(book,bindingResult);
        if(bindingResult.hasErrors())
            return "books/edit";
        booksDAO.update(id,book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("book") Book book, BindingResult bindingResult,
                         @PathVariable("id") int id, Model model){
        if(bindingResult.hasErrors())
            return "books/show";
        booksDAO.setOwner(id, book);
        return "redirect:/books/{id}";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksDAO.delete(id);
        return "redirect:/books";
    }
    @DeleteMapping("{id}/assign")
    public String returnToLibrary(@PathVariable("id") int id){
        booksDAO.unassign(id);
        return "redirect:/books/{id}";
    }
}
