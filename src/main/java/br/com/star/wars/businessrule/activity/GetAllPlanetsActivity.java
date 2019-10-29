package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.businessrule.navegable.GetAllPageableNavigable;
import br.com.star.wars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetAllPlanetsActivity implements Activity<GetAllPageableNavigable> {

    @Autowired
    PlanetRepository repository;
	@Autowired
    PlanetMapper mapper;
	
	@Override
	public void execute(ExecutionContext<GetAllPageableNavigable> context) {
		GetAllPageableNavigable navigable = context.getNavigableObject();
        if(navigable.getPageable() ==  null){
            navigable.setPageable(Pageable.unpaged());
        }
		Page<Planet> page = repository.findAll(navigable.getPageable());

		navigable.setPage(page.map(mapper::planetToPlanetDto));
		context.setExecutionResult(navigable);

	}
}
