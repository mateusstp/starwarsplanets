package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.entity.Planet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateListOfPlanetsActivityTest {

	PrepareEnvironmentValidateSwApiResponseActivity activity;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new PrepareEnvironmentValidateSwApiResponseActivity();
	}

	@Test
	public void validateListOfPlanet() {
		ExecutionContext<List<Planet>> context = new ExecutionContext<List<Planet>>(new ArrayList<>());
		activity.execute(context);
		List<Planet> result = context.getNavigableObject();
		assertNotNull(result);
	}



	@Test
	public void validateNullListOfPlanet() {
		ExecutionContext<List<Planet>> context = new ExecutionContext<List<Planet>>(null);
		activity.execute(context);
		assertTrue(context.isExecutionSuspended());
	}

}
