package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;
import br.com.star.wars.model.entity.Planet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrepareEnvironmentValidateSwApiResponseActivity implements Activity<List<Planet>> {

    @Override
    public void execute(ExecutionContext<List<Planet>> context) {
        List<Planet> planetList = context.getNavigableObject();

        if (planetList == null || planetList.isEmpty()) {
            context.suspendExecution();
            return;
        }

    }
}
