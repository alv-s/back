package com.backend.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    
	@JsonProperty("id")
    UUID id;
	
	@NotEmpty
	@NotNull
	@JsonProperty("nome")
    String nome;

	@NotEmpty
	@NotNull
	@JsonProperty("email")
	String email;

	@JsonProperty("idade")
	Integer idade;

}
