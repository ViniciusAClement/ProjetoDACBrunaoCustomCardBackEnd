package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_carbrand")
public class CarBrand implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carBrand_id;
	
	private String name;
	
	@OneToMany(mappedBy = "carBrand")
	private Set<Car> car = new HashSet<>();
	
	public CarBrand () {
		
	}

	public CarBrand(Long carBrand_id, String name) {
		super();
		this.carBrand_id = carBrand_id;
		this.name = name;
	}

	public Long getCarBrand_id() {
		return carBrand_id;
	}

	public void setCarBrand_id(Long carBrand_id) {
		this.carBrand_id = carBrand_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carBrand_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarBrand other = (CarBrand) obj;
		return Objects.equals(carBrand_id, other.carBrand_id);
	}
	
	
}
