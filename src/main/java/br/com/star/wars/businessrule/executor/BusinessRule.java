package br.com.star.wars.businessrule.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

public abstract class BusinessRule<T> implements ContextExecutor <T>{
	  List<Activity<T>> activities;
	  private String name;
	  private BusinessExecutor executor;

	  /**
	   * Construtor informando o nome e as atividades da regra de negócio
	   *
	   * @param name Nome da regra de negócio
	   * @param activities todas as atividades da regra de negócio. Precisa ser informada na ordem que
	   *        serão executadas
	   */
	  public BusinessRule(String name, Activity<T>... activities) {
	    if (name == null) {
	      this.name = this.getClass().getName();
	    } else {
	      this.name = name;
	    }
	    this.activities = new ArrayList<>(Arrays.asList(activities));
	  }

	  /**
	   * Obtem o nome da regra de negócio
	   *
	   * @return the value of name
	   */
	  public String getName() {
	    return name;
	  }

	  /**
	   * informa o executor que irá ser responsável por gerenciar as execuções desta regra de negócio
	   *
	   * @param executor a value for set
	   */
	  void setExecutor(BusinessExecutor executor) {
	    this.executor = executor;
	  }

	  /**
	   * Irá iniciar a uma execução para esta regra de negócio <br>
	   *
	   * @param navigableObject objeto que será navegado/aplicado a regra de negócio definida. Este
	   *        objeto está acessível atraves método {@link ExecutionContext#getNavigableObject()}
	   * @return a instância da execução realizada
	   * @see JusttoBusinessExecutor#executeRule(BusinessRule, Object) para mais detalhes de como
	   *      funciona o executor de regras de negócios
	   */
	  public ExecutionContext<T> execute(T navigableObject) {
	    if (executor == null) {
	      throw new NullPointerException("BusinessExecutor not defined!");
	    }
	    return executor.executeRule( this, navigableObject);
	  }

	  /**
	   * 
	   * @param activities
	   */
	  protected void registerActivities(Activity<T>... activities) {
	    if (this.activities == null || activities.length == 0) {
	      throw new RuntimeException("Nenhuma atividade informada para Regra de Negócio " + name);
	    }
	    this.activities.addAll(Arrays.asList(activities));
	  }

	  @PostConstruct
	  public abstract void postConstruct();
}
