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
@Table(name = "tb_car")
public class Car implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long car_id;
	
	private String name;
	
	private Integer yearOfCar;
	
	@ManyToOne
	@JoinColumn(name= "carbrand_id")
	private CarBrand carBrand;
	
    @ManyToMany
    @JoinTable(
        name = "car_product",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
	private Set<Product> products = new HashSet<>();
	
	public Car() {
		
	}

	public Car(Long car_id, String name, Integer yearOfCar) {
		super();
		this.car_id = car_id;
		this.name = name;
		this.yearOfCar = yearOfCar;
	}

	public Long getCar_id() {
		return car_id;
	}

	public void setCar_id(Long car_id) {
		this.car_id = car_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getyearOfCar() {
		return yearOfCar;
	}

	public void setyearOfCar(Integer yearOfCar) {
		this.yearOfCar = yearOfCar;
	}

	@Override
	public int hashCode() {
		return Objects.hash(car_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(car_id, other.car_id);
	}
	
	
}
