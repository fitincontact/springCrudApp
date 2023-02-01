package com.fitin.api;

import com.fitin.crud.dao.PersonDAO;
import com.fitin.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonDAO personDAO;

    @Operation(description = "create a person")
    @ApiResponses(value = {
            @ApiResponse(description = "Return string result", responseCode = "200")
    })
    @PostMapping()
    public String create(@RequestBody Person person) {
        return personDAO.create(person);
    }

    @Operation(description = "read all person")
    @ApiResponses(value = {
            @ApiResponse(description = "Return person list", responseCode = "200")
    })
    @GetMapping()
    public List<Person> read() {
        return personDAO.read();
    }

    @Operation(description = "read a person by id")
    @ApiResponses(value = {
            @ApiResponse(description = "Return a person", responseCode = "200")
    })
    @GetMapping("/{id}")
    public Person read(@PathVariable("id") int id) {
        return personDAO.read(id);
    }

    @Operation(description = "update a person")
    @ApiResponses(value = {
            @ApiResponse(description = "Return string result", responseCode = "200")
    })
    @PutMapping("/{id}")
    public String update(@RequestBody Person person,
                         @PathVariable("id") int id
    ) {
        personDAO.update(id, person);
        return "Update person with id: " + id;
    }

    @Operation(description = "delete a person")
    @ApiResponses(value = {
            @ApiResponse(description = "Return string result", responseCode = "200")
    })
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "Delete person with id: " + id;
    }
}
