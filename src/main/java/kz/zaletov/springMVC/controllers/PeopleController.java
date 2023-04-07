package kz.zaletov.springMVC.controllers;

import jakarta.validation.Valid;
import kz.zaletov.springMVC.DAO.PersonDAO;
import kz.zaletov.springMVC.models.Person;
import kz.zaletov.springMVC.services.PeopleService;
import kz.zaletov.springMVC.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonValidator personValidator;
    private final PersonDAO personDAO;
    private final PeopleService peopleService;
    @Autowired
    public PeopleController(PersonValidator personValidator, PersonDAO personDAO, PeopleService peopleService){
        this.personValidator = personValidator;
        this.personDAO = personDAO;
        this.peopleService = peopleService;
    }
    @GetMapping("")
    public String index(Model model){
//        model.addAttribute("people", peopleService.findAll());
        personDAO.testNPlus1();
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/new";
        peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
