package com.fitin.crud.dao;

import com.fitin.model.Persons;
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
    public String create(Persons persons) {
        Session session = sessionFactory.getCurrentSession();
        Persons person = read(persons);

        String result = "Person with this parameters is exist. " + persons;

        if (person == null) {
            session.save(persons);
            result = "Create new Person";
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<Persons> read() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Persons p", Persons.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Persons read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Persons.class, id);
    }

    @Transactional(readOnly = true)
    public Persons read(Persons persons) {
        Session session = sessionFactory.getCurrentSession();

        Query<Persons> query = session.createQuery("select p from Persons p where name = :name and age = :age and email = :email");
        query.setParameter("name", persons.getName());
        query.setParameter("age", persons.getAge());
        query.setParameter("email", persons.getEmail());

        return query.uniqueResult();

    }

    @Transactional
    public void update(int id, Persons updatedPersons) {
        Session session = sessionFactory.getCurrentSession();
        Persons personsToBeUpdated = session.get(Persons.class, id);

        personsToBeUpdated.setName(updatedPersons.getName());
        personsToBeUpdated.setAge(updatedPersons.getAge());
        personsToBeUpdated.setEmail(updatedPersons.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Persons.class, id));
    }
}
