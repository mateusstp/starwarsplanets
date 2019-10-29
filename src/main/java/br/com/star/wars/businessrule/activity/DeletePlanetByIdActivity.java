package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePlanetByIdActivity implements Activity<String> {

	@Autowired
    PlanetRepository repository;

	@Override
	  public void execute(ExecutionContext<String> executionContext) {
		repository.deleteById(executionContext.getNavigableObject());
		executionContext.setExecutionResult(executionContext.getNavigableObject());
	  }
}
