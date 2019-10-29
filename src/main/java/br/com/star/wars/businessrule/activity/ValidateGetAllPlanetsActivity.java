package br.com.star.wars.businessrule.activity;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.star.wars.businessrule.navegable.GetAllPageableNavigable;
import br.com.star.wars.businessrule.executor.Activity;
import br.com.star.wars.businessrule.executor.ExecutionContext;

@Component
public class ValidateGetAllPlanetsActivity implements Activity<GetAllPageableNavigable> {

	@Override
	public void execute(ExecutionContext<GetAllPageableNavigable> context) {
		GetAllPageableNavigable navegable = context.getNavigableObject();
		if(navegable == null){
			navegable = new GetAllPageableNavigable();
		}

		if(navegable.getPageable() == null){
			navegable.setPageable(Pageable.unpaged());
		}

		context.setNavigableObject(navegable);

	}
}
