package br.com.star.wars.businessrule.executor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;;

@Component
public class BusinessExecutor {
	private Logger log = LoggerFactory.getLogger(BusinessExecutor.class);
	@Autowired
	private ApplicationContext appContext;
	private final Map<String, BusinessRule> businessRules = new HashMap<String, BusinessRule>();

	@PostConstruct
	void postConstruct() {
		appContext.getBeansOfType(BusinessRule.class).values().forEach(this::registerBusinessRule);
	}

	/**
	 * Registra o {@link BusinessRule} no contexto do executor
	 *
	 * @param businessRule a instancia da regra de negócio que se deseja registrar
	 *                     no executor
	 */
	public void registerBusinessRule(BusinessRule businessRule) {
		this.businessRules.put(businessRule.getName(), businessRule);
		businessRule.setExecutor(this);
	}

	/**
	 * Cria um novo contexto para representar a execução da regra de negócio e
	 * executa as atividades da regra de negócio passando para as atividades o
	 * contexto criado. <br>
	 * Caso o executor esteja em debug (esta classe: {@link JusttoBusinessExecutor})
	 * ou o nome da regra de negócio esteja em debug, será possível acompanhar nos
	 * logs a execução das atividades, tempo decorrido em cada execução e o estado
	 * do objeto navegável antes de iniciar cada atividade e depois que foi
	 * executada a atividade.
	 *
	 * @param businessRule    instancia que representa a regra de negócio que será
	 *                        executada
	 * @param navigableObject objeto de entrada na regra de negócio. Este objeto é
	 *                        acessível nas atividades usando o méotod
	 *                        {@link ExecutionContext#getNavigableObject()}
	 * @return a instancia da execução que foi realizada
	 */
	public <E> ExecutionContext<E> executeRule(BusinessRule<E> businessRule, E navigableObject) {
		ExecutionContext<E> executionContext = new ExecutionContext<E>(navigableObject);
		StringBuffer buffer = new StringBuffer();
		Logger businessRuleLogger = LoggerFactory.getLogger(businessRule.getName());

		for (Activity<E> activity : businessRule.activities) {
			long startTime = System.currentTimeMillis();
			try {
				if (businessRuleLogger.isDebugEnabled()) {
					buffer.append("Preparing execution for navigableObject: ")
							.append(navigableObject == null ? null : navigableObject.toString()).append("\n");
				}
				if (executionContext.isExecutionSuspended()) {
					break;
				}
				activity.execute(executionContext);
				buffer.append("Executed ").append(activity.getClass().getName()).append(" in ")
						.append((System.currentTimeMillis() - startTime)).append(" milis\n");
				if (businessRuleLogger.isDebugEnabled()) {
					buffer.append("Current state for navigableObject: ")
							.append(navigableObject == null ? null : navigableObject.toString()).append("\n");
				}
			} catch (Exception e) {
				buffer.append("Error executing ").append(activity.getClass().getName()).append(". Time elapsed: ")
						.append((System.currentTimeMillis() - startTime)).append(" milis\n");
				log.error(buffer.toString(), e);
				throw new RuntimeException(e);
			} finally {
				executionContext.session.clear();
			}
		}
		if (log.isDebugEnabled()) {
			log.debug(buffer.toString());
		}
		if (businessRuleLogger.isDebugEnabled()) {
			businessRuleLogger.debug(buffer.toString());
		}
		return executionContext;
	}

}
