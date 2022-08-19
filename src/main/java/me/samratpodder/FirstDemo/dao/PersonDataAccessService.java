package me.samratpodder.FirstDemo.dao;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    @Override
    public int deletePersonbyID(UUID id) {
        String sql = "DELETE FROM person WHERE id='"+id+"'";
        try {
            jdbcTemplate.update(sql);
        } catch (DataAccessException e){
            return 0;
        } 
        return 1;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String sql = "INSERT INTO PERSON(id,fullname) VALUES('"+id+"','"+person.getName()+"')";
        try {
            jdbcTemplate.update(sql);
        } catch (DataAccessException e){
            return 0;
        } 
        return 1;
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
        // if(deletePersonbyID(id)==0) return 0;
        // if(insertPerson(id, person)==0) return 0;
        // return 1;
        String sql = "UPDATE PERSON SET FULLNAME=? WHERE ID=?";
        try {
            jdbcTemplate.update(sql,person.getName(),id);
        } catch (DataAccessException e) {
            return 0;
        }
        return 1;
    }
    
}
