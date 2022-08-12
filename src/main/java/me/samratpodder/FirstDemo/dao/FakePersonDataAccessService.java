package me.samratpodder.FirstDemo.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import me.samratpodder.FirstDemo.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new LinkedList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        for (Person p : DB) {
            System.out.println(p.getName());
        }
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonbyID(UUID id) {
        return DB.stream().filter(person->person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonbyID(UUID id) {
        Optional<Person> p = selectPersonbyID(id);
        if(p.isEmpty()) return 0;
        DB.remove(p.get());
        return 1;
    }

    @Override
    public int updatePersonbyID(UUID id, Person person) {
        int index=-1;
        for (int i = 0; i < DB.size(); i++) {
            if(DB.get(i).getId().equals(id)) index=i;
        }
        if (index==-1) {
            return 0;
        }
        DB.set(index, new Person(id,person.getName()));
        return 1;
    }
    
}
