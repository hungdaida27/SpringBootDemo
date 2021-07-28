package vn.techmaster.personmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.personmanager.model.Job;
import vn.techmaster.personmanager.repository.JobRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/job")
    public String allJob(Model model) {
        model.addAttribute("jobs", jobRepository.getAll());
        return "alljob";
    }

    @GetMapping("/add")
    public String addJob(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/save")
    public String saveJob(@Valid @ModelAttribute("job") Job job, BindingResult result, Model model) {
        Job newJob = jobRepository.addJob(job);
        if (result.hasErrors()) {
            return "jobform";
        }
        if (job.getId()>0){
            jobRepository.edit(job);
            model.addAttribute("jobs", jobRepository.getAll());
            return "alljob";
        }
            else
        {
            if (newJob != null) {
                model.addAttribute("jobs", jobRepository.getAll());
                return "alljob";
            }
            return "jobform";
        }
    }

    //Cap nhat Job
    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable("id") int id, Model model) {
        Optional<Job> job = jobRepository.get(id);
        if (job.isPresent()) {
            model.addAttribute("job", job.get());
        }
        return "jobform";
    }

    //Xoa job
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") int id, Model model) {
        jobRepository.deleteById(id);
        model.addAttribute("jobs", jobRepository.getAll());
        return "alljob";
    }
}
