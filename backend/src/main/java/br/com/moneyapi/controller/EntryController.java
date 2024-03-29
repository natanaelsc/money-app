package br.com.moneyapi.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyapi.events.EventResource;
import br.com.moneyapi.exceptions.AppExceptionHandler.Erro;
import br.com.moneyapi.exceptions.PersonDoesNotExistOrIsInactiveException;
import br.com.moneyapi.model.Entry;
import br.com.moneyapi.repository.filter.EntryFilter;
import br.com.moneyapi.repository.resume.EntryResume;
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
    @PreAuthorize("hasAuthority('ROLE_FIND_ENTRY') and hasAuthority('SCOPE_read')")
    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) {
        return entryService.filter(entryFilter, pageable);
    }

    @GetMapping(params = "resume")
    @PreAuthorize("hasAuthority('ROLE_FIND_ENTRY') and hasAuthority('SCOPE_read')")
    public Page<EntryResume> resume(EntryFilter entryFilter, Pageable pageable) {
        return entryService.resume(entryFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_ENTRY') and hasAuthority('SCOPE_read')")
    public ResponseEntity<Entry> getOne(@PathVariable Long id) {
        Entry entry = entryService.getOne(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_ENTRY') and hasAuthority('SCOPE_write')")
    public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
        Entry entrySaved = entryService.save(entry);
        publisher.publishEvent(new EventResource(this, response, entrySaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(entryService.save(entry));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REMOVE_ENTRY') and hasAuthority('SCOPE_write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        entryService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REGISTER_ENTRY') and hasAuthority('SCOPE_write')")
    public ResponseEntity<Entry> update(@PathVariable long id, @Valid @RequestBody Entry entry) {
        try {
			Entry entrySaved = entryService.update(id, entry);
			return ResponseEntity.ok(entrySaved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
    }
    
    @ExceptionHandler
    public ResponseEntity<Object> handlePersonDoesNotExistOrIsInactiveException(PersonDoesNotExistOrIsInactiveException ex) {
        String userMessage = messageSource.getMessage("person.does-not-exist-or-is-inactive", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<Erro> errors = Arrays.asList(new Erro(userMessage, developerMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
