package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import com.google.common.base.Strings;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;


public class ValidateSearchPlanetActivityTest {

	ValidateSearchPlanetActivity activity;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new ValidateSearchPlanetActivity();
	}

	@Test
	public void validateNavigable() {
		SearchNavigable navigable = new SearchNavigable();
		navigable.setTerm("Teste");
		ExecutionContext<SearchNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		SearchNavigable result = context.getNavigableObject();
		assertNotNull(result.getTerm());
		assertEquals("Teste", result.getTerm());

	}

	@Test
	public void validateNavigableEmpty() {
		SearchNavigable navigable = new SearchNavigable();
		navigable.setTerm("");
		ExecutionContext<SearchNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		assertTrue(context.isExecutionSuspended());

	}

	@Test
	public void invalidId() {
		SearchNavigable navigable = new SearchNavigable();
		ExecutionContext<SearchNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		assertTrue(context.isExecutionSuspended());
	}
}
