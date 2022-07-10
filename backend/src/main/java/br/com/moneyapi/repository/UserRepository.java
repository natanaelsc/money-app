package br.com.moneyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.moneyapi.model.UserDb;

@Repository
public interface UserRepository extends JpaRepository<UserDb, Long> {
    
    Optional<UserDb> findByEmail(String email);
}
