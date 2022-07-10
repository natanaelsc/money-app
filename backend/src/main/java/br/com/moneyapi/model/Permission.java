package br.com.moneyapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    private Long id;
    
    private String description;
}
