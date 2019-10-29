package br.com.star.wars.businessrule;

import br.com.star.wars.businessrule.executor.ContextExecutor;
import org.springframework.stereotype.Component;

@Component
public interface PlanetDelete extends ContextExecutor<String> {

}
