package br.com.star.wars.businessrule.impl;

import br.com.star.wars.businessrule.PrepareEnvironment;
import br.com.star.wars.businessrule.activity.PrepareEnvironmentGetAllPlanetsFromSwApiActivity;
import br.com.star.wars.businessrule.activity.PrepareEnvironmentSaveAllPlanetsToMongoActivity;
import br.com.star.wars.businessrule.activity.PrepareEnvironmentValidateSwApiResponseActivity;
import br.com.star.wars.businessrule.executor.BusinessRule;
import br.com.star.wars.model.entity.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrepareEnvironmentBR extends BusinessRule<List<Planet>> implements PrepareEnvironment {
	@Autowired
	PrepareEnvironmentGetAllPlanetsFromSwApiActivity getAllPlanetsFromSwApiActivity;
	@Autowired
	PrepareEnvironmentValidateSwApiResponseActivity validateSwApiResponseActivity;
	@Autowired
	PrepareEnvironmentSaveAllPlanetsToMongoActivity saveAllPlanetsToMongoActivity;
	/** Construtor informando o nome e as atividades da regra de negócio */
	public PrepareEnvironmentBR() {
		super("BUILD ENVIRONMENT");
	}

	@Override
	public void postConstruct() {
		registerActivities(getAllPlanetsFromSwApiActivity, validateSwApiResponseActivity, saveAllPlanetsToMongoActivity);
	}

}
