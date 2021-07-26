package vn.techmaster.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.crud.model.Person;
import vn.techmaster.crud.repository.PersonRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("people", personRepository.getAll());
        return "allpeople";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    @PostMapping("/save")
    public String save(Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        if (person.getId() > 0) {
            personRepository.edit(person);
        } else {
            personRepository.create(person);
        }
        return "redirect:/person";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personRepository.get(id);
        if (person.isPresent()) {
            model.addAttribute("person", person);
        }
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteByID(@PathVariable("id") int id) {
        personRepository.deleteById(id);
        return "redirect:/person";
    }

    @GetMapping("/search")
    public String searchPerson(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        if (name == "") {
            model.addAttribute("people", personRepository.getAll());
            return "redirect:/person";
        } else {
            Person person = personRepository.search(name);
            model.addAttribute("people", person);
            return "allpeople";
        }
    }
}
