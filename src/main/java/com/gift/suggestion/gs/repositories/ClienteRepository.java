package com.gift.suggestion.gs.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gift.suggestion.gs.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID>{
	
}
