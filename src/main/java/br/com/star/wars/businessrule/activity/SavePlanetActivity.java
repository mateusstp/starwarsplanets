package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePlanetActivity implements Activity<PlanetDto> {

    @Autowired
    PlanetMapper mapper;

    @Autowired
    PlanetRepository repository;

    @Override
    public void execute(ExecutionContext<PlanetDto> executionContext) {
        PlanetDto planetDto = executionContext.getNavigableObject();
        executionContext.setExecutionResult(mapper.planetToPlanetDto(
                repository.save(mapper.planetDtoToPlanet(planetDto))));

    }
}
