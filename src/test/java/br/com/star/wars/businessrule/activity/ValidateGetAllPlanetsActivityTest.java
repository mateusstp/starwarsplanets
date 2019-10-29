package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.navegable.GetAllPageableNavigable;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.mapper.PlanetMapper;
import br.com.star.wars.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import br.com.star.wars.businessrule.executor.ExecutionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class ValidateGetAllPlanetsActivityTest {

	ValidateGetAllPlanetsActivity activity;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		activity = new ValidateGetAllPlanetsActivity();
	}

	@Test
	public void validateNavigableWithoutPage() {
		GetAllPageableNavigable navigable = new GetAllPageableNavigable();
		ExecutionContext<GetAllPageableNavigable> context = new ExecutionContext<>(navigable);
		activity.execute(context);
		GetAllPageableNavigable result = context.getNavigableObject();
		assertNotNull(result.getPageable());
	}

	@Test
	public void validateNavigableNull() {
		ExecutionContext<GetAllPageableNavigable> context = new ExecutionContext<>(null);
		activity.execute(context);
		GetAllPageableNavigable result = context.getNavigableObject();
		assertNotNull(result.getPageable());
	}
}
