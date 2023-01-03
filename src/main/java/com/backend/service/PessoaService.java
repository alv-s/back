package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.backend.dto.PessoaDto;
import com.backend.model.Pessoa;
import com.backend.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public PessoaDto cadastrarPessoa(@Valid PessoaDto dto) throws Exception {
		
		if (null == dto.getEmail()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail é obrigatório.");
		}
		
		Optional<Pessoa> pessoaOpt = pessoaRepository.findByEmail(dto.getEmail());
		Pessoa pessoa = null;
		
		if (pessoaOpt.isPresent()) {
			pessoa = pessoaOpt.get();
			
			if (!pessoa.isDeleted()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail em uso.");
			}
			
		}
		
		if (null != pessoa) {
			pessoa.setNome(dto.getNome());
			pessoa.setEmail(dto.getEmail());
			pessoa.setIdade(dto.getIdade());
			pessoa.setDeleted(false);
			
		} else {
			pessoa = Pessoa.builder()
						.nome(dto.getNome())
						.email(dto.getEmail())
						.idade(dto.getIdade())
					.build();
			
		}
		
		
		pessoaRepository.save(pessoa);
		
		return PessoaDto.builder()
				.id(pessoa.getId())
				.nome(pessoa.getNome())
				.email(pessoa.getEmail())
				.idade(pessoa.getIdade())
			.build();
	}
	
	public PessoaDto editarPessoa(UUID pessoaId, @Valid PessoaDto dto) throws Exception {
		
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
		
		Optional<Pessoa> pessoaOpt = pessoaRepository.findByEmail(dto.getEmail());
		
		if (pessoaOpt.isPresent() && !pessoaOpt.get().getId().equals(pessoa.getId())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail em uso.");
		}
		
		pessoa.setNome(dto.getNome());
		pessoa.setEmail(dto.getEmail());
		pessoa.setIdade(dto.getIdade());
		
		pessoaRepository.save(pessoa);
		
		return PessoaDto.builder()
				.id(pessoa.getId())
				.nome(pessoa.getNome())
				.email(pessoa.getEmail())
				.idade(pessoa.getIdade())
			.build();
	}
	
	public PessoaDto pegarPessoa(UUID pessoaId) {
		
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
		
		return PessoaDto.builder()
				.id(pessoa.getId())
				.nome(pessoa.getNome())
				.email(pessoa.getEmail())
				.idade(pessoa.getIdade())
			.build();
	}
	
	public List<PessoaDto> listarPessoas() {
		
		List<Pessoa> pessoas = pessoaRepository.findAllDeletedFalse();
		List<PessoaDto> pessoasDto = new ArrayList<>();
		
		pessoas.forEach(p -> {
			pessoasDto.add(
				PessoaDto.builder()
					.id(p.getId())
					.nome(p.getNome())
					.email(p.getEmail())
					.idade(p.getIdade())
				.build()
			);
		});
		
		
		return pessoasDto;
	}
	
	public void deletarPessoa(UUID pessoaId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Pessoa não encontrada."));
		
		pessoa.setDeleted(true);
		
		pessoaRepository.save(pessoa);
	}
}
