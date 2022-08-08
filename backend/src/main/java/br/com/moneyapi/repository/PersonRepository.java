package br.com.moneyapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.moneyapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByNameContaining(String name, Pageable pageable);
}
