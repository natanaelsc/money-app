package br.com.moneyapi.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    
    @NotNull
    private String name;
    
    @Embedded
    private Address address;
    
    @NotNull
    private boolean active;
}
