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
import org.springframework.web.bind.annotation.PutMapping;
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
		publisher.publishEvent(new EventResource(this, response, personSaved.getId()));	
		return ResponseEntity.status(HttpStatus.CREATED).body(personSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person person = personService.getOne(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
		personService.update(id, person);
		return ResponseEntity.ok(person);
	}

	@PutMapping("/{id}/active")
	public ResponseEntity<Person> updateStatus(@PathVariable Long id, @RequestBody Boolean active) {
		personService.setStatus(id, active);
		return ResponseEntity.ok().build();
	}
}
