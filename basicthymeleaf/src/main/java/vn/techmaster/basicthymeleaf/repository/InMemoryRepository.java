package vn.techmaster.basicthymeleaf.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.basicthymeleaf.model.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryRepository {
    private ArrayList<Person> people;

    public InMemoryRepository(){
        people = new ArrayList<>(List.of(
                new Person("Cường", "Vietnam", "1975-11-27", "male"),
                new Person("Hùng", "USA", "1994-10-27", "male"),
                new Person("Toyota", "Vietnam", "1999-10-27", "female"),
                new Person("Honda", "Russia", "1998-02-01", "female"),
                new Person("Mazda", "Vietnam", "1992-02-01", "female"),
                new Person("Suzuki", "Laos", "2001-05-06", "female"),
                new Person("Yamaha", "Campuchia", "1998-11-20", "male"),
                new Person("KIA", "Conggo", "1992-10-20", "female")
        ));
    }

    public List<Person> getPeople() {
        return people;
    }

}
