package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateIdActivityTest {

	ValidateIdActivity activity;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new ValidateIdActivity();
	}

	@Test
	public void validateNavigable() {
		String navigable = "1L";
		ExecutionContext<String> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		String result = context.getNavigableObject();
		assertNotNull(result);
	}



	@Test
	public void validateInvalidId() {
		String navigable = "-1L";
		ExecutionContext<String> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		String result = context.getNavigableObject();
		assertNotNull(result);
	}

	@Test
	public void validateIdNull() {
		ExecutionContext<String> context = new ExecutionContext<>(null);
		activity.execute(context);
		assertTrue(context.isExecutionSuspended());
	}

}
