package br.com.moneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.moneyapi.model.Person;
import br.com.moneyapi.repository.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) { return personRepository.save(person); }

    public Person getOne(Long id) { return findById(id); }

    public void delete(Long id) { personRepository.deleteById(id); }

    public Person update(Long id, Person person) {
        Person personSaved = findById(id);
        BeanUtils.copyProperties(person, personSaved, "id");
        return personRepository.save(personSaved);
    }

    public Person setStatus(Long id, Boolean active) {
        Person personSaved = findById(id);
        personSaved.setActive(active);
        return personRepository.save(personSaved);
    }

    public Page<Person> findByNameContaining(String name, Pageable pageable) {
        return personRepository.findByNameContaining(name, pageable);
    }

    private Person findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
