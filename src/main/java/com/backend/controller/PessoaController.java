package com.backend.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.dto.PessoaDto;
import com.backend.service.PessoaService;

@Controller
@RequestMapping(path = "/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@PostMapping("")
	public ResponseEntity<PessoaDto> criarPessoa(@Valid @RequestBody PessoaDto dto) throws Exception {

		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrarPessoa(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaDto> editarPessoa(@PathVariable(name = "id") UUID pessoaId, @Valid @RequestBody PessoaDto dto) throws Exception {
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.editarPessoa(pessoaId, dto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> pegarPessoa(@PathVariable(name = "id") UUID pessoaId) throws Exception {
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.pegarPessoa(pessoaId));
	}
	
	@GetMapping("")
	public ResponseEntity<List<PessoaDto>> listarPessoas() throws Exception {
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPessoas());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<PessoaDto>> deletarPessoa(@PathVariable(name = "id") UUID pessoaId) throws Exception {
		
		pessoaService.deletarPessoa(pessoaId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
