package me.samratpodder.FirstDemo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import me.samratpodder.FirstDemo.model.Person;

public interface PersonDao {
    int insertPerson(UUID id, Person person);
    default int addPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> selectAllPeople();
    
    Optional<Person> selectPersonbyID(UUID id);

    int deletePersonbyID(UUID id);
    int updatePersonbyID(UUID id, Person person);
}