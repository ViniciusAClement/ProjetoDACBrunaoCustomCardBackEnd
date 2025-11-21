package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_marketcar")
public class MarketCar implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long marketCar_id;
	
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy = "marketcar")
	private Set<MarketCarItem> marketCarItens = new HashSet<>();
	
	public MarketCar() {
		
	}

	public MarketCar(Long marketCar_id) {
		super();
		this.marketCar_id = marketCar_id;
	}

	public Long getMarketCar_id() {
		return marketCar_id;
	}

	public void setMarketCar_id(Long marketCar_id) {
		this.marketCar_id = marketCar_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(marketCar_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketCar other = (MarketCar) obj;
		return Objects.equals(marketCar_id, other.marketCar_id);
	}
	
	
}
