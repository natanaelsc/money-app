package br.com.moneyapi.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyapi.events.EventResource;
import br.com.moneyapi.model.Person;
import br.com.moneyapi.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
    
    @Autowired
    private PersonService personService;

	@Autowired
	private ApplicationEventPublisher publisher;

    @PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person personSaved = personService.save(person);
		publisher.publishEvent(new EventResource(this, response, personSaved.getCode()));	
		return ResponseEntity.status(HttpStatus.CREATED).body(personSaved);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Person> findByCode(@PathVariable Long code) {
        Person person = personService.getOne(code);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Void> delete(@PathVariable Long code) {
		personService.delete(code);
		return ResponseEntity.noContent().build();
	}
}
