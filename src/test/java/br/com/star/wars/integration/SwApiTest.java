package br.com.star.wars.integration;

import br.com.star.wars.integration.swapi.SwApi;
import br.com.star.wars.model.entity.Film;
import br.com.star.wars.model.entity.Planet;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
@Ignore
public class SwApiTest {

    @Test
    public void getFilmTest() throws Exception {
        Film film = SwApi.getFilmById("5");
        assertEquals("Attack of the Clones",film.getTitle());
        assertEquals("2",film.getEpisode_id());
    }

    @Test
    public void getPlanetTest() throws Exception {
        Planet planet= SwApi.getPlanetById("1");
        String expected = "Tatooine";
        assertEquals(expected,planet.getName());
    }
}

