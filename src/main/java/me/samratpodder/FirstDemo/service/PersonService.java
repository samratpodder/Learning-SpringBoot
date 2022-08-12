package me.samratpodder.FirstDemo.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import me.samratpodder.FirstDemo.dao.PersonDao;
import me.samratpodder.FirstDemo.model.Person;

@Service
public class PersonService {
    private final PersonDao personDAO;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDAO) {
        this.personDAO = personDAO;
    }
    public int insertPerson(Person person) {
        return personDAO.addPerson(person);
    }
    public List<Person> getAllPeople(){
        return personDAO.selectAllPeople();
    }
    public Optional<Person> getPersonByID(UUID id){
        return personDAO.selectPersonbyID(id);
    }
    public int deletePersonbyID(UUID id){
        return personDAO.deletePersonbyID(id);
    }
    public int updatePersonbyID(UUID id, Person newPerson){
        return personDAO.updatePersonbyID(id, newPerson);
    }
}
