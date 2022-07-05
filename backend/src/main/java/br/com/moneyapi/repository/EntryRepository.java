package br.com.moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.moneyapi.model.Entry;
import br.com.moneyapi.repository.query.EntryRepositoryQuery;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery {}
