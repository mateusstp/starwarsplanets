package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.integration.swapi.SwApi;
import br.com.star.wars.model.entity.Planet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrepareEnvironmentGetAllPlanetsFromSwApiActivity implements Activity<List<Planet>> {

	@Override
	public void execute(ExecutionContext<List<Planet>> context) throws Exception {
		context.setNavigableObject(SwApi.getAllPlanets());
	}
}
