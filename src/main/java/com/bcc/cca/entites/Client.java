package com.bcc.cca.entites;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//notations do JPA
@Entity
@Table(name = "tb_client")

//notations do lobok
@Getter
@Setter
public class Client extends User {
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Adress> adresses = new HashSet<>();

	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private MarketCar marketCar;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<CardInfo> cardInfo = new HashSet<>();
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Order> order = new HashSet<>();

	public void addAdress(Adress adress){
		adresses.add(adress);
	}
}
