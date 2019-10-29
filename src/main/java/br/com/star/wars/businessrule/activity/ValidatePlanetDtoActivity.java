package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.dto.PlanetDto;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

@Component
public class ValidatePlanetDtoActivity implements Activity<PlanetDto> {

	@Override
	  public void execute(ExecutionContext<PlanetDto> executionContext) {
		PlanetDto planetDto = executionContext.getNavigableObject();
		if(Strings.isNullOrEmpty(planetDto.getName())){
			executionContext.suspendExecution();
			return;
		}
	  }
}
