package br.com.star.wars.businessrule.impl;

import br.com.star.wars.businessrule.PlanetGetOne;
import br.com.star.wars.businessrule.activity.GetOneByIdPlanetNavigableActivity;
import br.com.star.wars.businessrule.activity.ValidatePlanetNavigableActivity;
import br.com.star.wars.businessrule.executor.BusinessRule;
import br.com.star.wars.businessrule.navegable.PlanetNavigable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlanetGetOneBR extends BusinessRule<PlanetNavigable> implements PlanetGetOne {
	/**
	 * - Valida se id
	 * - deleta planeta
	 **/
	@Autowired
	ValidatePlanetNavigableActivity validatePlanetNavigableActivity;
	@Autowired
	GetOneByIdPlanetNavigableActivity getOneByIdPlanetNavigableActivity;
	/** Construtor informando o nome e as atividades da regra de negócio */
	public PlanetGetOneBR() {
		super("GET ONE PLANET");
	}

	@Override
	public void postConstruct() {
		registerActivities(validatePlanetNavigableActivity, getOneByIdPlanetNavigableActivity);
	}

}
