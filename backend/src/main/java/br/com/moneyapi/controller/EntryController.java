package br.com.moneyapi.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyapi.events.EventResource;
import br.com.moneyapi.exceptions.AppExceptionHandler.Erro;
import br.com.moneyapi.exceptions.PersonDoesNotExistOrIsInactiveException;
import br.com.moneyapi.model.Entry;
import br.com.moneyapi.repository.filter.EntryFilter;
import br.com.moneyapi.service.EntryService;


@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<List<Entry>> getAll(EntryFilter entryFilter) {
        return ResponseEntity.ok(entryService.filter(entryFilter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getOne(@PathVariable Long id) {
        Entry entry = entryService.getOne(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry entrySaved = entryService.save(entry);
        publisher.publishEvent(new EventResource(this, response, entrySaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entryService.save(entry));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        entryService.delete(id);
    }
    
    @ExceptionHandler
    public ResponseEntity<Object> handlePersonDoesNotExistOrIsInactiveException(PersonDoesNotExistOrIsInactiveException ex) {
        String userMessage = messageSource.getMessage("person.does-not-exist-or-is-inactive", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<Erro> errors = Arrays.asList(new Erro(userMessage, developerMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
