package vn.techmaster.personmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.personmanager.model.Person;
import vn.techmaster.personmanager.repository.PersonRepository;
import vn.techmaster.personmanager.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("person", new Person());
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "success";
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

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public String save(@Valid @ModelAttribute("person") Person person, BindingResult result, Model moddel) throws Exception {
        if (person.getPhoto().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("person", "photo", "Photo is required"));
        }
        if (person.getId() > 0) {
            personRepository.edit(person);
        } else {
            personRepository.create(person);
        }
        storageService.uploadFile(person.getPhoto(), person.getId());
        moddel.addAttribute("people", personRepository.getAll());
        return "allpeople";
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

    @GetMapping(value = "/{id}")
    public String showDetail(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personRepository.get(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        }
        return "personinfo";
    }
}
