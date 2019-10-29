package br.com.star.wars.businessrule.impl;

import br.com.star.wars.businessrule.PlanetSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.star.wars.businessrule.activity.SearchPlanetByNameActivity;
import br.com.star.wars.businessrule.activity.ValidateSearchPlanetActivity;
import br.com.star.wars.businessrule.navegable.SearchNavigable;
import br.com.star.wars.businessrule.executor.BusinessRule;

@Component
public class PlanetSearchBR extends BusinessRule<SearchNavigable> implements PlanetSearch {
	/**
	 * - Valida se id
	 * - deleta planeta
	 **/
	@Autowired
	ValidateSearchPlanetActivity validateSearchPlanetActivity;
	@Autowired
	SearchPlanetByNameActivity searchPlanetByNameActivity;
	
	/** Construtor informando o nome e as atividades da regra de negócio */
	public PlanetSearchBR() {
		super("DELETE PLANET");
	}

	@Override
	public void postConstruct() {
		registerActivities(validateSearchPlanetActivity,searchPlanetByNameActivity);
	}

}
