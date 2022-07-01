package br.com.moneyapi.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.moneyapi.model.Person;
import br.com.moneyapi.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person personSaved = personService.save(person);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
				.buildAndExpand(personSaved.getCode()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(personSaved);
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Person> findByCode(@PathVariable Long code) {
        Person person = personService.getOne(code);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}
}
