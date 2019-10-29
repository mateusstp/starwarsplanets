package br.com.star.wars.businessrule.executor;
/**
 * CLASS DOCUMENTATION <br>
 * ---------------------- <br>
 * PURPOSE: <br>
 * Executor de contexto da regra de contexto <br>
 * @param <T> the type of navigable object
 */
public interface ContextExecutor<T> {
	 /**
	   * Executa o contexto de regra de negócio
	   * 
	   * @param navigableObject
	   * @return
	   */
	ExecutionContext execute(T navegableObject);
}
