package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;

import java.util.List;

@Component
public class SearchPlanetByNameActivity implements Activity<SearchNavigable> {

	@Autowired
    PlanetRepository repository;
	@Autowired
    PlanetMapper mapper;

	@Override
	public void execute(ExecutionContext<SearchNavigable> context) {
		SearchNavigable navigable = context.getNavigableObject();
		List<Planet> planetList = repository.findByNameContainingIgnoreCase(navigable.getTerm());
		if(!planetList.isEmpty()){
			navigable.setDtos(mapper.planetsToPlanetDtos(planetList));

		}

		context.setExecutionResult(navigable);
	}
}
