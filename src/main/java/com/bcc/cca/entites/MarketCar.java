package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "client_id", unique = true)
	private Client client;
	
	@OneToMany(mappedBy = "marketcar")
	private Set<MarketCarItem> marketCarItens = new HashSet<>();
	
	public MarketCar() {
		
	}

	public MarketCar(Long marketCar_id) {
		super();
		this.id = marketCar_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long marketCar_id) {
		this.id = marketCar_id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<MarketCarItem> getMarketCarItens() {
		return marketCarItens;
	}
	
	public void addItem(MarketCarItem item) {
        marketCarItens.add(item);
        item.setMarketcar(this);
    }

    public void removeItem(MarketCarItem item) {
        marketCarItens.remove(item);
        item.setMarketcar(null);
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
	
}
