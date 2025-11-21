package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Adress> adresses = new HashSet<>();

	@OneToMany (mappedBy = "client")
	private Set<CardInfo> cardInfo = new HashSet<>();
	
	@OneToMany (mappedBy = "client")
	private Set<Order> order = new HashSet<>();
	
	
	public Client () {
		
	}

	public Client(Long id_user, String name, String email, String phone, String password, String cpf) {
		super(id_user, name, email, phone, password, cpf);
	}

	public Set<Adress> getAdress() {
		return adresses;
	}

	public Set<CardInfo> getCardInfo() {
		return cardInfo;
	}

	public Set<Order> getOrder() {
		return order;
	}
	
}
