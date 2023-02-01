package com.fitin.crud.dao;

import com.fitin.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public String create(Person personNew) {
        Session session = sessionFactory.getCurrentSession();
        Person personOld = read(personNew);

        String result = "Person with this parameters is exist. " + personNew;

        if (personOld == null) {
            session.save(personNew);
            result = "Create new Person " + personNew;
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<Person> read() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional(readOnly = true)
    public Person read(Person person) {
        Session session = sessionFactory.getCurrentSession();

        Query<Person> query = session.createQuery("from Person p where name = :name and age = :age and email = :email");
        query.setParameter("name", person.getName());
        query.setParameter("age", person.getAge());
        query.setParameter("email", person.getEmail());

        return query.uniqueResult();
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
