package com.practice.springjdbc.repository;

import com.practice.springjdbc.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
        return namedQuery.getResultList();
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);// JPA
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person insert(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

    public List<Person> findByName(String name) {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_by_name", Person.class);
//        Query query = entityManager.createNamedQuery("find_by_name");
        namedQuery.setParameter("name", name);

        return namedQuery.getResultList();
    }

    public List<Person> findByNameAndLocation(String name, String location) {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_by_name_and_location", Person.class);
//        Query query = entityManager.createNamedQuery("find_by_name");
        namedQuery.setParameter("name", name);
        namedQuery.setParameter("location", location);

        return namedQuery.getResultList();
    }
}
