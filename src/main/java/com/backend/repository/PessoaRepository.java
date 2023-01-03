package com.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, UUID> {
	
	Optional<Pessoa> findByEmail(@Param("email") String email);
	
	@Query(nativeQuery = true, value = "SELECT p.* "
			+ " FROM pessoa p"
			+ " WHERE p.id = :id "
			+ " AND p.deleted = false ")
	Optional<Pessoa> findById(@Param("id") UUID id);
	
	@Query(nativeQuery = true, value = "SELECT p.* "
			+ " FROM pessoa p "
			+ " WHERE p.deleted = false ")
	List<Pessoa> findAllDeletedFalse();
}
