package br.com.star.wars.businessrule.activity;

import br.com.star.wars.businessrule.navegable.SearchNavigable;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;

@Component
public class ValidateSearchPlanetActivity implements Activity<SearchNavigable> {

	@Override
	public void execute(ExecutionContext<SearchNavigable> context) {
		SearchNavigable navigable = context.getNavigableObject();
		if(navigable == null || Strings.isNullOrEmpty(navigable.getTerm())){
			context.suspendExecution();
			return;
		}
	}
}
