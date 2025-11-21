package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

public class Adress implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id_adress;
	
	private String street;
	
	private Integer number;
	
	private String zipcode;
	
	private String state;
	
	public Adress() {
		
	}

	public Adress(Long id_adress, String street, Integer number, String zipcode, String state) {
		super();
		this.id_adress = id_adress;
		this.street = street;
		this.number = number;
		this.zipcode = zipcode;
		this.state = state;
	}

	public Long getId_adress() {
		return id_adress;
	}

	public void setId_adress(Long id_adress) {
		this.id_adress = id_adress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_adress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adress other = (Adress) obj;
		return Objects.equals(id_adress, other.id_adress);
	}
	
}
