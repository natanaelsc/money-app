package br.com.moneyapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserDb {
    
    @Id
    private Long id;
    
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
        name = "user_permission", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
	private List<Permission> permissions;
}
