package kz.zaletov.springMVC.repositories;

import kz.zaletov.springMVC.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameOrderByYear(String name);

    List<Person> findByNameStartingWith(String startingWith);

    List<Person> findByNameOrYear(String name, int year);
}
