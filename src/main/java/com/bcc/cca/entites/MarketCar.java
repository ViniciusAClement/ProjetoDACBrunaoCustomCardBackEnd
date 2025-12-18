package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//notations do JPA
@Entity
@Table(name = "tb_marketcar")

//notations do lobok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MarketCar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private Double totalValue;

	@OneToOne
	@JoinColumn(name = "client_id", unique = true)
	private Client client;
	
	@OneToMany(mappedBy = "marketcar")
	private Set<MarketCarItem> marketCarItens = new HashSet<>();
	
	public void addItem(MarketCarItem item) {
        marketCarItens.add(item);
        item.setMarketcar(this);
    }

    public void removeItem(MarketCarItem item) {
        marketCarItens.remove(item);
        item.setMarketcar(null);
    }
	
}
