package br.com.star.wars.businessrule.impl;

import br.com.star.wars.businessrule.PlanetDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.star.wars.businessrule.activity.DeletePlanetByIdActivity;
import br.com.star.wars.businessrule.activity.ValidateIdActivity;
import br.com.star.wars.businessrule.executor.BusinessRule;

@Component
public class PlanetDeleteBR extends BusinessRule<String> implements PlanetDelete {
	/**
	 * - Valida se id
	 * - deleta planeta
	 **/
	@Autowired ValidateIdActivity validateIdActivity;
	@Autowired DeletePlanetByIdActivity deletePlanetByIdActivity;
	/** Construtor informando o nome e as atividades da regra de negócio */
	public PlanetDeleteBR() {
		super("DELETE PLANET");
	}

	@Override
	public void postConstruct() {
		registerActivities(validateIdActivity,deletePlanetByIdActivity);
	}

}
