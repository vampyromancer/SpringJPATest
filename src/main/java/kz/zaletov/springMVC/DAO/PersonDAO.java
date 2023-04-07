package kz.zaletov.springMVC.DAO;

import jakarta.persistence.EntityManager;
import kz.zaletov.springMVC.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);
        List <Person> people = session.createQuery("select p from Person p LEFT JOIN FETCH p.items", Person.class).getResultList();
        for (Person person: people
             ) {
            System.out.println("Person " + person.getName() + " has " + person.getItems());
        }

        //1 Запрос - получим всех людей
//        List<Person> people = session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//        for (Person person : people)
//            System.out.println("Person" + person.getName() + " has: " + person.getItems());
    }
}
