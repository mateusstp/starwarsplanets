package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;

import java.util.Optional;

@Component
public class GetOneByIdPlanetNavigableActivity implements Activity<PlanetNavigable> {

	@Autowired
    PlanetRepository repository;
	@Autowired
    PlanetMapper mapper;
	@Override
	  public void execute(ExecutionContext<PlanetNavigable> executionContext) {
		PlanetNavigable navigable = executionContext.getNavigableObject();
		if(navigable.getDto() == null || navigable.getDto().getId() == null){
			executionContext.suspendExecution();
			return;
		}
		Optional<Planet> planet =  repository.findOneById(navigable.getDto().getId());
		planet.ifPresent(planet1 -> navigable.setDto(mapper.planetToPlanetDto(planet1)));
		executionContext.setExecutionResult(navigable);
	  }
}
