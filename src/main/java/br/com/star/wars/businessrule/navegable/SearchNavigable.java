package br.com.star.wars.businessrule.navegable;

import java.util.ArrayList;
import java.util.List;

import br.com.star.wars.model.dto.PlanetDto;

public class SearchNavigable {
	private String term;
	private List<PlanetDto> dtos = new ArrayList<>();

	public SearchNavigable(String term) {
		this.term = term;
	}
	public SearchNavigable() {

	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<PlanetDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<PlanetDto> dtos) {
		this.dtos = dtos;
	}
}
