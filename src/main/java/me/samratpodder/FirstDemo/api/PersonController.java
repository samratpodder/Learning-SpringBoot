package me.samratpodder.FirstDemo.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.samratpodder.FirstDemo.model.Person;
import me.samratpodder.FirstDemo.service.PersonService;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Validated @NonNull @RequestBody Person person){
        personService.insertPerson(person);
    }
    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}")
    public Person getPersonbyID(@PathVariable("id") UUID id)
    {
        return personService.getPersonByID(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deletePersonbyID(@PathVariable("id" )UUID id){
        personService.deletePersonbyID(id);
    }

    @PutMapping(path = "{id}")
    public void updatePersonbyID(@PathVariable("id") UUID id, @NonNull @Validated @RequestBody Person personTobeUpdated){
        personService.updatePersonbyID(id, personTobeUpdated);
    }
}
