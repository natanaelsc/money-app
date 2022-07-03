package br.com.moneyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.moneyapi.model.Entry;
import br.com.moneyapi.repository.EntryRepository;

@Service
public class EntryService {
    
    @Autowired
    private EntryRepository entryRepository;

    public List<Entry> getAll() { return entryRepository.findAll(); }

    public Entry getById(Long id) { return findById(id); }

    private Entry findById(Long id) {
        return entryRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}
