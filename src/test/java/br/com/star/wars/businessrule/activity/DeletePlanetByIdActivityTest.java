package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.repository.PlanetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class DeletePlanetByIdActivityTest {

    @Mock
    PlanetRepository repository;

    DeletePlanetByIdActivity activity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        activity = new DeletePlanetByIdActivity();
        activity.repository = repository;
        lenient().doNothing().when(repository).deleteById(any(String.class));

    }

    @Test
    public void deleteById() {
        ExecutionContext<String> context = new ExecutionContext<>("1L");
        activity.execute(context);
        Optional<Long> result = context.getExecutionResult();
        assertTrue(result.isPresent());
        assertEquals(result.get(),"1L");
    }
}
