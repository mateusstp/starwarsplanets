package br.com.star.wars.businessrule.impl;

import br.com.star.wars.businessrule.PlanetSave;
import br.com.star.wars.businessrule.activity.GetPlanetDtoByNameActivity;
import br.com.star.wars.businessrule.activity.SavePlanetActivity;
import br.com.star.wars.businessrule.activity.ValidatePlanetDtoActivity;
import br.com.star.wars.businessrule.executor.BusinessRule;
import br.com.star.wars.model.dto.PlanetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlanetSaveBR extends BusinessRule<PlanetDto> implements PlanetSave {
	@Autowired
	ValidatePlanetDtoActivity validatePlanetDtoActivity;
	@Autowired
	GetPlanetDtoByNameActivity getPlanetDtoByNameActivity;
	@Autowired
	SavePlanetActivity savePlanetActivity;
	/** Construtor informando o nome e as atividades da regra de negócio */
	public PlanetSaveBR() {
		super("DELETE PLANET");
	}

	@Override
	public void postConstruct() {
		registerActivities(validatePlanetDtoActivity, getPlanetDtoByNameActivity, savePlanetActivity);
	}

}
