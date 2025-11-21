package com.bcc.cca.entites;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_admin")
public class Admin extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Admin() {
	}

	public Admin(Long id_user, String name, String email, String phone, String password, String cpf) {
		super(id_user, name, email, phone, password, cpf);
	}
	
	
}
