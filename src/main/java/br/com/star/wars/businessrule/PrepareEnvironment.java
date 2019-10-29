package br.com.star.wars.businessrule;

import br.com.star.wars.businessrule.executor.ContextExecutor;
import br.com.star.wars.model.entity.Planet;

import java.util.List;

public interface PrepareEnvironment extends ContextExecutor<List<Planet>> {

}
