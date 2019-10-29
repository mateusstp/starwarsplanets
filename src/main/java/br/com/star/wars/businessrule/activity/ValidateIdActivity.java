package br.com.star.wars.businessrule.activity;
import br.com.star.wars.businessrule.executor.Activity;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import br.com.star.wars.businessrule.executor.ExecutionContext;

@Component
public class ValidateIdActivity implements Activity<String> {

	@Override
	  public void execute(ExecutionContext<String> executionContext) {
		String id = executionContext.getNavigableObject();
		if(Strings.isNullOrEmpty(id)){
			executionContext.suspendExecution();
			return;
		}
	  }
}
