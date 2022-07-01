package br.com.moneyapi.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
    
	private String street;
	private String number;
	private String complement;
	private String district;;
	private String zip;
	private String city;
	private String state;
}
