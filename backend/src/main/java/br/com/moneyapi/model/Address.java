package br.com.moneyapi.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
    
	private String street;
	private String number;
	private String complement;
	private String district;;
	private String zipCode;
	private String city;
	private String state;
}
