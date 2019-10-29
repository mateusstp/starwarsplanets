package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrepareEnvironmentSaveAllPlanetsToMongoActivity implements Activity<List<Planet>> {

	@Autowired
	PlanetRepository repository;
	@Override
	public void execute(ExecutionContext<List<Planet>> context) throws Exception {
		List<Planet> planetList = context.getNavigableObject();
		repository.saveAll(planetList);

	}
}
