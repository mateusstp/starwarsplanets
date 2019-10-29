package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.navegable.GetAllPageableNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class GetAllPlanetsActivityTest{
	@Mock
    PlanetRepository repository;
	@Mock
    PlanetMapper mapper;
	Page<Planet> pagePlanets;
	Page<PlanetDto> pagePlanetsDto;
	GetAllPlanetsActivity activity;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		activity = new GetAllPlanetsActivity();
		activity.mapper = mapper;
		activity.repository = repository;
		Planet planet1 = new Planet();
		planet1.setClimate("teste");
		pagePlanets = new PageImpl<>(Collections.singletonList(planet1));
		PlanetDto planetDto = new PlanetDto();
		planetDto.setClimate("teste");
		pagePlanetsDto = new PageImpl<>(Collections.singletonList(planetDto));
		lenient().doReturn(planetDto).when(mapper).planetToPlanetDto(any(Planet.class));
		lenient().doReturn(pagePlanets).when(repository).findAll(any(Pageable.class));

	}

	@Test
	public void getAllPlanets() {
		GetAllPageableNavigable navigable = new GetAllPageableNavigable();
		navigable.setPageable(Pageable.unpaged());
		ExecutionContext<GetAllPageableNavigable> context = new ExecutionContext<GetAllPageableNavigable>(navigable);
		activity.execute(context);
		Optional<GetAllPageableNavigable> result = context.getExecutionResult();
		assertTrue(result.isPresent());
		assertTrue(result.get().getPage().hasContent());
		assertEquals(result.get().getPage().getTotalElements(), 1L);
	}

	@Test
	public void getAllPlanetsWithoutPageable() {
		GetAllPageableNavigable navigable = new GetAllPageableNavigable();
		ExecutionContext<GetAllPageableNavigable> context = new ExecutionContext<GetAllPageableNavigable>(navigable);
		activity.execute(context);
		Optional<GetAllPageableNavigable> result = context.getExecutionResult();
		assertTrue(result.isPresent());
		assertTrue(result.get().getPage().hasContent());
		assertEquals(result.get().getPage().getTotalElements(), 1L);
	}
}
