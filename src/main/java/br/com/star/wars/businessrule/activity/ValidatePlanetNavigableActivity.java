package br.com.star.wars.businessrule.activity;

import org.springframework.stereotype.Component;
import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;

@Component
public class ValidatePlanetNavigableActivity implements Activity<PlanetNavigable> {

	@Override
	  public void execute(ExecutionContext<PlanetNavigable> executionContext) {
		PlanetNavigable navigable = executionContext.getNavigableObject();
		if(navigable.getDto() == null || navigable.getDto().getId() == null){
			executionContext.suspendExecution();
			return;
		}
	  }
}
