package br.com.star.wars.businessrule.navegable;

import br.com.star.wars.model.entity.Planet;
import br.com.star.wars.model.dto.PlanetDto;

public class PlanetNavigable {
	private Planet entity;
	private PlanetDto dto;

	public PlanetNavigable(PlanetDto dto) {
		this.dto = dto;
	}

	public PlanetNavigable() {
	}

	public Planet getEntity() {
		return entity;
	}

	public void setEntity(Planet entity) {
		this.entity = entity;
	}

	public PlanetDto getDto() {
		return dto;
	}

	public void setDto(PlanetDto dto) {
		this.dto = dto;
	}
}
