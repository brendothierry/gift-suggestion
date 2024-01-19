package com.gift.suggestion.gs.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gift.suggestion.gs.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID>{
	
	@Query("SELECT c.email, c.senha, c.id FROM ClienteModel c WHERE c.email = :email")
    Optional<Object[]> getByEmail(@Param("email") String email);
    
}
