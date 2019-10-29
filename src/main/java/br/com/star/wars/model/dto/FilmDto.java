package br.com.star.wars.model.dto;

import java.io.Serializable;
public class FilmDto implements Serializable {
	private String title;
	private String episode_id;

	public FilmDto() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEpisode_id() {
		return episode_id;
	}

	public void setEpisode_id(String episode_id) {
		this.episode_id = episode_id;
	}
}
