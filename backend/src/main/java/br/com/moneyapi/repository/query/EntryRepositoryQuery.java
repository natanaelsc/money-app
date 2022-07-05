package br.com.moneyapi.repository.query;

import java.util.List;

import br.com.moneyapi.model.Entry;
import br.com.moneyapi.repository.filter.EntryFilter;

public interface EntryRepositoryQuery {
    
    public List<Entry> filter(EntryFilter entryFilter);
}
