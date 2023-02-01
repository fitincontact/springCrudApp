package com.fitin.api;

import com.fitin.crud.PersonRepository;
import com.fitin.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdj")
public class SpringDataJPAController {

    private final PersonRepository repository;

    public SpringDataJPAController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<Person> read() {
        return repository.findAll();
    }
}
