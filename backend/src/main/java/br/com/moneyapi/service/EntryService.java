package br.com.moneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.moneyapi.exceptions.PersonDoesNotExistOrIsInactiveException;
import br.com.moneyapi.model.Entry;
import br.com.moneyapi.model.Person;
import br.com.moneyapi.repository.EntryRepository;
import br.com.moneyapi.repository.PersonRepository;
import br.com.moneyapi.repository.filter.EntryFilter;
import br.com.moneyapi.repository.resume.EntryResume;

@Service
public class EntryService {
    
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private PersonRepository personRepository;

    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable) { 
        return entryRepository.filter(entryFilter, pageable); 
    }

    public Page<EntryResume> resume(EntryFilter entryFilter, Pageable pageable) { 
        return entryRepository.resume(entryFilter, pageable); 
    }

    public Entry getOne(Long id) { return findById(id); }

    public Entry save(Entry entry) { 
        Optional<Person> person = personRepository.findById(entry.getPerson().getId());
        if (person.isEmpty() || person.get().isInactive()) {
            throw new PersonDoesNotExistOrIsInactiveException();
        }
        return entryRepository.save(entry); 
    }

    public Entry update(long id, Entry entry) {
        Entry entrySaved = findById(id);
		if (!entry.getPerson().equals(entrySaved.getPerson())) {
			validPerson(entry);
		}
		BeanUtils.copyProperties(entry, entrySaved, "id");
		return entryRepository.save(entrySaved);
    }

    public void delete(Long id) { entryRepository.deleteById(id); }

    private Entry findById(Long id) {
        return entryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    private void validPerson(Entry entry) {
        Optional<Person> person = null;
		if (entry.getPerson().getId() != null) {
			person = personRepository.findById(entry.getPerson().getId());
		}
		if (person.isEmpty() || person.get().isInactive()) {
			throw new PersonDoesNotExistOrIsInactiveException();
		}
    }
}
