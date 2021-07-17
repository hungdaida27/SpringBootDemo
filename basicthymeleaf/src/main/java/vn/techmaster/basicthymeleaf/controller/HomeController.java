package vn.techmaster.basicthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.basicthymeleaf.repository.InMemoryRepository;

@Controller
public class HomeController {
    @Autowired private InMemoryRepository repo;

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/people")
    public String getPeople(Model model){
        model.addAttribute("people",repo.getPeople());
        return "people";
    }

    @GetMapping("/2row")
    public String getPeopleRow(Model model) {
        model.addAttribute("people", repo.getPeople());
        return "2row";
    }
}
