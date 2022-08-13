package me.samratpodder.FirstDemo.dao;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import me.samratpodder.FirstDemo.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addPerson(Person person) {
        return PersonDao.super.addPerson(person);
    }

    @Override
    public int deletePersonbyID(UUID id) {
        return 0;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT * FROM person";
         return jdbcTemplate.query(sql, (resultSet,i)-> {
            return new Person(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("fullname")
            );
        });
    }

    @Override
    public Optional<Person> selectPersonbyID(UUID id) {
        final String sql = "SELECT * from person where id = ?";
        Person person = jdbcTemplate.queryForObject(
            sql,
            (resultSet,i)->{
                return new Person(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("fullname")
                );
            },
            id
        );
        return Optional.ofNullable(person);
    }

    @Override
    public int updatePersonbyID(UUID id, Person person) {
        return 0;
    }
    
}
