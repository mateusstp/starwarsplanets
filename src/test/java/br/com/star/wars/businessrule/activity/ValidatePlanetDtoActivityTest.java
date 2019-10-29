package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.dto.FilmDto;
import br.com.star.wars.model.dto.PlanetDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class ValidatePlanetDtoActivityTest {

	ValidatePlanetDtoActivity activity;

	PlanetDto dto;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new ValidatePlanetDtoActivity();
		dto = new PlanetDto();
		dto.setName("Name");
		dto.setClimate("Climate");
		dto.setTerrain("Terrain");
		FilmDto filmDto = new FilmDto();
		filmDto.setEpisode_id("Episode_id");
		filmDto.setTitle("Title");
		dto.setMovies(new ArrayList<>(Collections.singleton(filmDto)));
	}

	@Test
	public void validatePlanetDto() {

		ExecutionContext<PlanetDto> context = new ExecutionContext<>(dto);
		activity.execute(context);
		PlanetDto result = context.getNavigableObject();
		assertNotNull(result);
		assertNotNull(result.getClimate());
		assertEquals("Name",result.getName());

	}

	@Test
	public void invalidId() {

		ExecutionContext<PlanetDto> context = new ExecutionContext<>(new PlanetDto());
		activity.execute(context);
		assertTrue(context.isExecutionSuspended());
	}
}
