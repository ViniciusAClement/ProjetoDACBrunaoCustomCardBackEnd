package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_adress")
public class Adress implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idadress;
	
	private String street;
	
	private Integer number;
	
	private String zipcode;
	
	private String state;
	
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Adress() {
		
	}

	public Adress(Long id_adress, String street, Integer number, String zipcode, String state, String city, Client client) {
		super();
		this.idadress = id_adress;
		this.street = street;
		this.number = number;
		this.zipcode = zipcode;
		this.state = state;
		this.city = city;
		this.client = client;
	}

	public Long getId() {
		return idadress;
	}

	public void setId(Long id_adress) {
		this.idadress = id_adress;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idadress);
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
		return Objects.equals(idadress, other.idadress);
	}
	
}
