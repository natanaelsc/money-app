package br.com.moneyapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyapi.events.EventResource;
import br.com.moneyapi.model.Entry;
import br.com.moneyapi.service.EntryService;


@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<Entry>> getAll() {
        return ResponseEntity.ok(entryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getOne(@PathVariable Long id) {
        Entry entry = entryService.getById(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry entrySaved = entryService.save(entry);
        publisher.publishEvent(new EventResource(this, response, entrySaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entryService.save(entry));
    }
    
}
