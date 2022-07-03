package br.com.moneyapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moneyapi.model.Entry;
import br.com.moneyapi.service.EntryService;


@RestController
@RequestMapping("/entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @GetMapping
    public ResponseEntity<List<Entry>> getAll() {
        return ResponseEntity.ok(entryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getOne(@PathVariable Long id) {
        Entry entry = entryService.getById(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }
    
}
