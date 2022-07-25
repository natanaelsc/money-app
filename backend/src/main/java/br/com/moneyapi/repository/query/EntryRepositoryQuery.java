package br.com.moneyapi.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.moneyapi.model.Entry;
import br.com.moneyapi.repository.filter.EntryFilter;
import br.com.moneyapi.repository.resume.EntryResume;

public interface EntryRepositoryQuery {
    
    public Page<Entry> filter(EntryFilter entryFilter, Pageable pageable);
    public Page<EntryResume> resume(EntryFilter entryFilter, Pageable pageable);
}
