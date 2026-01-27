package com.bcc.cca.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//notations do JPA
@Entity
@Table(name = "tb_admin")
//notations do lobok
@Getter
@Setter
public class Admin extends User {
}
