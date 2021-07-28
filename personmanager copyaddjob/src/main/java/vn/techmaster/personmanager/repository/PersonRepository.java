package vn.techmaster.personmanager.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.personmanager.model.Job;
import vn.techmaster.personmanager.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
    private List<Person> people = new ArrayList<>();

    public List<Person> getAll() {
        return people;
    }
    //Add Person
    public Person create(Person person) {
        int id;
        if (people.isEmpty()) {
            id = 1;
        } else {
            Person lastPerson = people.get(people.size() - 1);
            id = lastPerson.getId() + 1;
        }
        person.setId(id);
        people.add(person);
        return person;
    }
//Edit Person
    public Person edit(Person person) {
        get(person.getId()).ifPresent(exisPerson -> {
            exisPerson.setName(person.getName());
            exisPerson.setJob(person.getJob());
            exisPerson.setGender(person.isGender());
            exisPerson.setBirthDay(person.getBirthDay());
            if(person.getPhoto().getOriginalFilename().isEmpty()){
                exisPerson.setPhoto(person.getPhoto());
            }
        });
        return person;
    }
//Get id;
    public Optional<Person> get(int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst();
    }
//Delete Person
    public void deleteById(int id) {
        get(id).ifPresent(existed -> people.remove(existed));
    }

    public void delete(Person person) {
        deleteById(person.getId());
    }
//Search name
    public Person search(String name) {
        return people.stream().filter(p -> p.getName().contains(name)).findAny().orElse(null);
    }
}
