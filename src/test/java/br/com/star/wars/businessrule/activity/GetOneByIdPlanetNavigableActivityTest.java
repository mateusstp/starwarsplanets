package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class GetOneByIdPlanetNavigableActivityTest {

    @Mock
    PlanetRepository repository;
    @Mock
    PlanetMapper mapper;

    GetOneByIdPlanetNavigableActivity activity;
    Planet entity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        activity = new GetOneByIdPlanetNavigableActivity();
        activity.mapper = mapper;
        activity.repository = repository;
        entity = new Planet();
        entity.setId("1L");
        entity.setClimate("teste");
        PlanetDto planetDto = new PlanetDto();
        planetDto.setClimate("teste");
        planetDto.setId("1L");
        lenient().doReturn(planetDto).when(mapper).planetToPlanetDto(any(Planet.class));
        lenient().doReturn(Optional.of(entity)).when(repository).findOneById(any(String.class));

    }

    @Test
    public void getOneById() {
        PlanetNavigable navigable = new PlanetNavigable();
        navigable.setDto(new PlanetDto("1L"));
        ExecutionContext<PlanetNavigable> context = new ExecutionContext<>(navigable);
        activity.execute(context);
        Optional<PlanetNavigable> result = context.getExecutionResult();
        assertTrue(result.isPresent());
        assertNotNull(result.get().getDto());
        //assertNotNull(result.get().getEntity());
        assertEquals(result.get().getDto().getId(), entity.getId());
        assertEquals(result.get().getDto().getClimate(), entity.getClimate());

    }

    @Test
    public void getOneByIdWithoutId() {
        PlanetNavigable navigable = new PlanetNavigable();
        ExecutionContext<PlanetNavigable> context = new ExecutionContext<>(navigable);
        activity.execute(context);
        Optional<PlanetNavigable> result = context.getExecutionResult();
        assertFalse(result.isPresent());
    }
}
