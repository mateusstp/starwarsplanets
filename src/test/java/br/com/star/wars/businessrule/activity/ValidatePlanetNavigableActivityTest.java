package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ValidatePlanetNavigableActivityTest {
	ValidatePlanetNavigableActivity activity;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new ValidatePlanetNavigableActivity();
	}

	@Test
	public void validateNavigable() {
		PlanetNavigable navigable = new PlanetNavigable();
		navigable.setDto(new PlanetDto("1L"));
		ExecutionContext<PlanetNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		PlanetNavigable result = context.getNavigableObject();
		assertNotNull(result.getDto());
		assertNotNull(result.getDto().getId());
		assertEquals("1L",result.getDto().getId());

	}

	@Test
	public void invalidId() {
		PlanetNavigable navigable = new PlanetNavigable();
		ExecutionContext<PlanetNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		assertTrue(context.isExecutionSuspended());
	}
}
