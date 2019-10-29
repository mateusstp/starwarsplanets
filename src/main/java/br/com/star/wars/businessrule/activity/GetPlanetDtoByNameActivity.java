package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.impl.PlanetSearchBR;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetPlanetDtoByNameActivity implements Activity<PlanetDto> {

    @Autowired
    PlanetSearchBR planetSearch;

    @Override
    public void execute(ExecutionContext<PlanetDto> executionContext) {
        PlanetDto planetDto = executionContext.getNavigableObject();
        ExecutionContext<SearchNavigable> context = planetSearch.execute(new SearchNavigable(planetDto.getName()));
        Optional<SearchNavigable> result = context.getExecutionResult();
        if (result.isPresent() && result.get().getDtos() != null && !result.get().getDtos().isEmpty()) {
			executionContext.setExecutionResult(result.get().getDtos().stream().findAny().get());
			executionContext.suspendExecution();
			return;
        }

    }
}
