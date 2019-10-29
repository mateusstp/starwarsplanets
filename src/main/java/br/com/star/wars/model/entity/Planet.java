package br.com.star.wars.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Planets")
public class Planet {
	@Id
	private String id;
	private String name;
	private String climate;
	private String terrain;
	private List<Film> movies = new ArrayList<Film>();

	public Planet() {
	}

	public Planet(String id) {
		this.id = id;
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

	public List<Film> getMovies() {
		return movies;
	}

	public void setMovies(List<Film> movies) {
		this.movies = movies;
	}
}
