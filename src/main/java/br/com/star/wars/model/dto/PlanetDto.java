package br.com.star.wars.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class PlanetDto implements Serializable {

	private String id;
	@NotNull
	private String name;
	@NotNull
	private String climate;
	@NotNull
	private String terrain;
	
	private List<FilmDto> movies;

	public PlanetDto(String id) {
		this.id = id;
	}


	public PlanetDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getMovieAppearances() {
		return movies != null ? movies.size() : 0;
	}

	public List<FilmDto> getMovies() {
		return movies;
	}

	public void setMovies(List<FilmDto> movies) {
		this.movies = movies;
	}
}
