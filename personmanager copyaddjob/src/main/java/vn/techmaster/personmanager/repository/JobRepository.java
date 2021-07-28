package vn.techmaster.personmanager.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.personmanager.model.Job;
import vn.techmaster.personmanager.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JobRepository {
    private List<Job> jobs = new ArrayList<>();
//List Job
    public JobRepository(){
        jobs.add(new Job(1, "Developer"));
        jobs.add(new Job(2, "Teacher"));
        jobs.add(new Job(3, "Singer"));
    }

    public List<Job> getAll(){
        return jobs;
    }
//Add new Job
    public Job addJob(Job job){
        int id;
        if(jobs.isEmpty()){
            id =1;
        }else {
            Job lastJob = jobs.get(jobs.size()-1);
            id = lastJob.getId()+1;
        }
        job.setId(id);
        for (Job i:
             jobs) {
            if(job.getName().equalsIgnoreCase(i.getName())){
                return i;
            }
        }
        jobs.add(job);
        return job;
    }


    //Cap nhat job
    public Job edit(Job job){
        get(job.getId()).ifPresent(existJob->{
            existJob.setName(job.getName());
        });
        return job;
    }

    //Xoa job
    public void delete(Job job){
        deleteById(job.getId());
    }

    public Optional<Job> get(int id) {
        return jobs.stream().filter(j -> j.getId() == id).findFirst();
    }

    public void deleteById(int id) {
        get(id).ifPresent(existed -> jobs.remove(existed));
    }
}
