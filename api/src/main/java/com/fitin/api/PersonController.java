package com.fitin.api;

import com.fitin.crud.dao.PersonDAO;
import com.fitin.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonDAO personDAO;

    @PostMapping()
    public String create(@RequestBody Person person) {
        return personDAO.create(person);
    }

    @GetMapping()
    public List<Person> read() {
        return personDAO.read();
    }

    @GetMapping("/{id}")
    public Person read(@PathVariable("id") int id) {
        return personDAO.read(id);
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Person person,
                         @PathVariable("id") int id
    ) {
        personDAO.update(id, person);
        return "Update person with id: " + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "Delete person with id: " + id;
    }
}
