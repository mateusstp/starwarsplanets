package br.com.star.wars.integration.swapi;

import br.com.star.wars.model.entity.Film;
import br.com.star.wars.model.entity.Planet;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SwApi {

    private static String filmUrl = "https://swapi.co/api/films/";
    private static String planetUrl = "https://swapi.co/api/planets/";
    static Logger log = LoggerFactory.getLogger(SwApi.class);

    public static List<Planet> getAllPlanets() throws Exception {
        JsonObject jsonObject = getRequest(new HttpGet("https://swapi.co/api/planets/"));
        List<Planet> planetList = new ArrayList<>(getAllPlanetsByJsonArray(jsonObject.getAsJsonArray("results")));
        while (!jsonObject.get("next").isJsonNull() && !Strings.isNullOrEmpty(removeQuotationMarks(jsonObject.get("next").getAsString()))) {
            jsonObject = getRequest(new HttpGet(removeQuotationMarks(jsonObject.get("next").getAsString())));
            planetList.addAll(getAllPlanetsByJsonArray(jsonObject.getAsJsonArray("results")));
        }
        return planetList;
    }


    public static List<Planet> getAllPlanetsByJsonArray(JsonArray planetArray) throws Exception {

        JsonObject jsonObject = getRequest(new HttpGet("https://swapi.co/api/planets/"));
        JsonArray results = jsonObject.getAsJsonArray("results");
        List<Planet> finalPlanets = new ArrayList<>();
        results.forEach(p -> finalPlanets.add(swApiJsonToPlanet(p.getAsJsonObject())));
        return finalPlanets;

    }


    public static Planet swApiJsonToPlanet(JsonObject jsonObject) {
        Planet planet = new Planet();
        planet.setName(removeQuotationMarks(jsonObject.get("name").getAsString()));
        planet.setClimate(removeQuotationMarks(jsonObject.get("climate").getAsString()));
        planet.setTerrain(removeQuotationMarks(jsonObject.get("terrain").getAsString()));
        List<Film> movies = new ArrayList<>();
        JsonArray films = jsonObject.getAsJsonArray("films");

        films.forEach(filmUrl -> {
            try {
                movies.add(getFilmByUrl(String.valueOf(filmUrl)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        if (!movies.isEmpty()) {
            planet.getMovies().addAll(movies);
        }

        return planet;
    }

    public static Film getFilmByUrl(String url) throws IOException {
        Film film = new Film();
        url = removeQuotationMarks(url);
        JsonObject jsonObject = getRequest(new HttpGet(url));
        film.setEpisode_id(removeQuotationMarks(jsonObject.get("episode_id").getAsString()));
        film.setTitle(removeQuotationMarks(jsonObject.get("title").getAsString()));
        film.setSwApiUrl(url);
        return film;
    }

    public static Planet getPlanetByUrl(String url) throws IOException {
        return swApiJsonToPlanet(getRequest(new HttpGet(removeQuotationMarks(url))));
    }

    public static Film getFilmById(String id) throws Exception {
        return getFilmByUrl(filmUrl + id);
    }

    public static Planet getPlanetById(String id) throws Exception {
        return getPlanetByUrl(planetUrl + id);
    }


    public static String removeQuotationMarks(String str) {
        return !Strings.isNullOrEmpty(str) ? str.replace("\"", "") : str;
    }

    public static JsonObject getRequest(HttpGet getRequest) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject jsonObject = deserialize(stringBuilder.toString());
        bufferedReader.close();

        return jsonObject;
    }

    public static JsonObject deserialize(String json) {
        Gson gson = new Gson();
        JsonObject jsonClass = gson.fromJson(json, JsonObject.class);
        return jsonClass;
    }

    public static JsonObject innerRequest(String uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        return getRequest(httpGet);
    }
}
