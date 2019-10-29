package br.com.star.wars.businessrule.impl;

import br.com.star.wars.businessrule.PlanetGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.star.wars.businessrule.activity.GetAllPlanetsActivity;
import br.com.star.wars.businessrule.activity.ValidateGetAllPlanetsActivity;
import br.com.star.wars.businessrule.navegable.GetAllPageableNavigable;
import br.com.star.wars.businessrule.executor.BusinessRule;

@Component
public class PlanetGetAllBR extends BusinessRule<GetAllPageableNavigable> implements PlanetGetAll {
	@Autowired ValidateGetAllPlanetsActivity validateGetAllPlanetsActivity;
	@Autowired GetAllPlanetsActivity getAllPlanetsActivity;
	/** Construtor informando o nome e as atividades da regra de negócio */
	public PlanetGetAllBR() {
		super("GET PLANETS");
	}

	@Override
	public void postConstruct() {
		registerActivities(validateGetAllPlanetsActivity, getAllPlanetsActivity);
	}

}
