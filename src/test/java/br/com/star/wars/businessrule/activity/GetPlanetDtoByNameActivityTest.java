package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.impl.PlanetSearchBR;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.dto.FilmDto;
import br.com.star.wars.model.dto.PlanetDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class GetPlanetDtoByNameActivityTest {

	@Mock
	PlanetSearchBR planetSearch;

	GetPlanetDtoByNameActivity activity;

	PlanetDto dto;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new GetPlanetDtoByNameActivity();
		activity.planetSearch=planetSearch;
		dto = new PlanetDto();
		dto.setName("Name");
		dto.setClimate("Climate");
		dto.setTerrain("Terrain");
		FilmDto filmDto = new FilmDto();
		filmDto.setEpisode_id("Episode_id");
		filmDto.setTitle("Title");
		dto.setMovies(new ArrayList<>(Collections.singleton(filmDto)));
		SearchNavigable navigable = new SearchNavigable();
		navigable.setDtos(Collections.singletonList(dto));
		ExecutionContext<SearchNavigable> context = new ExecutionContext<>(navigable);
		context.setExecutionResult(navigable);
		lenient().doReturn(context).when(planetSearch).execute(any());
	}

	@Test
	public void searchByNameFound() {

		ExecutionContext<PlanetDto> context = new ExecutionContext<>(dto);
		activity.execute(context);
		PlanetDto result = context.getNavigableObject();
		assertNotNull(result);
		assertNotNull(result.getClimate());
		assertEquals("Name",result.getName());
		assertTrue(context.isExecutionSuspended());

	}


	@Test
	public void searchByNameNotFound() {

		ExecutionContext<SearchNavigable> contextSearch = new ExecutionContext<>(new SearchNavigable());
		lenient().doReturn(contextSearch).when(planetSearch).execute(any());
		ExecutionContext<PlanetDto> context = new ExecutionContext<>(dto);
		activity.execute(context);
		PlanetDto result = context.getNavigableObject();
		assertNotNull(result);
		assertNotNull(result.getClimate());
		assertEquals("Name",result.getName());
		assertFalse(context.isExecutionSuspended());

	}


}
