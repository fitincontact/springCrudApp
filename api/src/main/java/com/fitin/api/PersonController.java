package com.fitin.api;

import com.fitin.crud.dao.PersonDAO;
import com.fitin.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonDAO personDAO;

    @PostMapping()
    public String create(@RequestBody Persons persons) {
        return personDAO.create(persons);
    }

    @GetMapping()
    public List<Persons> read() {
        return personDAO.read();
    }

    @GetMapping("/{id}")
    public Persons read(@PathVariable("id") int id) {
        return personDAO.read(id);
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Persons persons,
                         @PathVariable("id") int id
    ) {
        personDAO.update(id, persons);
        return "Update persons with id: " + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "Delete person with id: " + id;
    }
}
