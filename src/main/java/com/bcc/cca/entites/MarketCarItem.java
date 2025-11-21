package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_marketcaritem")
public class MarketCarItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long MarketCarItem_id;
	
	private Integer quantity;
	
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "marketcar_id")
	private MarketCar marketcar;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public MarketCarItem() {
		
	}

	public MarketCarItem(Long marketCarItem_id, Integer quantity, Double price) {
		super();
		MarketCarItem_id = marketCarItem_id;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getMarketCarItem_id() {
		return MarketCarItem_id;
	}

	public void setMarketCarItem_id(Long marketCarItem_id) {
		MarketCarItem_id = marketCarItem_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public MarketCar getMarketcar() {
		return marketcar;
	}

	public void setMarketcar(MarketCar marketcar) {
		this.marketcar = marketcar;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(MarketCarItem_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketCarItem other = (MarketCarItem) obj;
		return Objects.equals(MarketCarItem_id, other.MarketCarItem_id);
	}
	
	
}
