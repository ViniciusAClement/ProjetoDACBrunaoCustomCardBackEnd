package com.bcc.cca.entites;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id_user;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private String cpf;
	
	public User() {
		
	}

	public User(Long id_user, String name, String email, String phone, String password, String cpf) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.cpf = cpf;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id_user, other.id_user);
	}
	
	
}
