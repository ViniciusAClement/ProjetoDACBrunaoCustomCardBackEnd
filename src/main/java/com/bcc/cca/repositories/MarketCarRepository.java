package com.bcc.cca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bcc.cca.entites.Client;
import com.bcc.cca.entites.MarketCar;

public interface MarketCarRepository extends JpaRepository<MarketCar, Long> {
	
	Optional<MarketCar> findByClient(Client client);
	
	@Query("SELECT mc FROM MarketCar mc LEFT JOIN FETCH mc.marketCarItens WHERE mc.client = :client")
	Optional<MarketCar> findByClientWithItems(@Param("client") Client client);
}
