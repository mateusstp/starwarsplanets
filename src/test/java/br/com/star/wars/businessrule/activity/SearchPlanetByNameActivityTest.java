package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class SearchPlanetByNameActivityTest {

	@Mock
	PlanetRepository repository;
	@Mock
	PlanetMapper mapper;

	SearchPlanetByNameActivity activity;

	List<Planet> planetList = new ArrayList<>();

	List<PlanetDto> planetDtoList = new ArrayList<>();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new SearchPlanetByNameActivity();
		activity.mapper = mapper;
		activity.repository = repository;
		planetList.add(new Planet("1L"));
		planetList.add(new Planet("2L"));
		planetList.add(new Planet("3L"));
		planetDtoList.add(new PlanetDto("1L"));
		planetDtoList.add(new PlanetDto("2L"));
		planetDtoList.add(new PlanetDto("3L"));
		lenient().doReturn(planetDtoList).when(mapper).planetsToPlanetDtos(any());
		lenient().doReturn(planetList).when(repository).findByNameContainingIgnoreCase(any(String.class));

	}

	@Test
	public void searchByName() {
		SearchNavigable navigable = new SearchNavigable();
		navigable.setTerm("Teste");
		ExecutionContext<SearchNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		Optional<SearchNavigable> result = context.getExecutionResult();
		assertTrue(result.isPresent());
		assertEquals(result.get().getDtos().size(), 3L);
	}

	@Test
	public void searchByNameNull() {
		SearchNavigable navigable = new SearchNavigable();
		ExecutionContext<SearchNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		Optional<SearchNavigable> result = context.getExecutionResult();
		assertTrue(result.isPresent());
		assertEquals(result.get().getDtos().size(), 0L);
	}
}
