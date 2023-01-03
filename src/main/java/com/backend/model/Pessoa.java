package com.backend.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;
	
	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@NotEmpty
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "idade", nullable = false)
	private int idade;
	
	@Column(name = "deleted")
	private boolean deleted = false;
	
}
