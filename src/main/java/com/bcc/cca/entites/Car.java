package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long car_id;
	
	private String name;
	
	private Integer year;
	
	public Car() {
		
	}

	public Car(Long car_id, String name, Integer year) {
		super();
		this.car_id = car_id;
		this.name = name;
		this.year = year;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
