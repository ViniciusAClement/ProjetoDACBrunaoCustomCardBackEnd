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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "category_product",
	joinColumns = @JoinColumn(name ="category_id"),
	inverseJoinColumns = @JoinColumn(name = "products_id"))
	private Set<Product> products = new HashSet<>();
	
	public Category() {
		
	}

	public Category(Long category_id, String name) {
		super();
		this.id = category_id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long category_id) {
		this.id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}
	
	public void addProduct(Product p) {
	    products.add(p);
	}

	public void removeProduct(Product p) {
	    products.remove(p);
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
