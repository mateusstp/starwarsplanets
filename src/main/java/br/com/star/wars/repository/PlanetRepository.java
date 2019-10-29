package br.com.star.wars.repository;

import br.com.star.wars.model.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {
	
	List<Planet> findByNameContainingIgnoreCase(String name);
	
	List<Planet> findByClimateContainingIgnoreCase(String name);
	
	List<Planet> findByTerrainContainingIgnoreCase(String name);
	
	Optional<Planet> findOneById(String id);

}
