package br.com.star.wars.businessrule.executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class ExecutionContext<T> {
	Map<String, Object> session = new HashMap<String, Object>();
	private List<Message> messages = new ArrayList<Message>();
	private boolean executionSuspended;
	private Object executionResult;
	private T navigableObject;

	/**
	 * @param navigableObject objeto a ser navegado na regra de negócio
	 */
	public ExecutionContext(T navigableObject) {
		this.navigableObject = navigableObject;
	}

	/**
	 * Armazena um objeto na sessão da execução atual <br>
	 * O isolamento é por execução, isto significa que duas execução da mesma regra
	 * de negócio não compartilham objetos.
	 *
	 * @param key A chave utilizada para armazenar o objeto
	 * @param obj O objeto que irá ser armazenado
	 * @return caso já exist aum objeto armazenado com a mesma chave, ele será
	 *         substituído, mas o que já estava armazenado será retornado
	 */
	public <E> E store(final String key, E obj) {
		return (E) session.put(key, obj);
	}

	/**
	 * Consulta um objeto na sessão com a chave informada
	 *
	 * @param key a chave do objeto que se deseja consultar
	 * @return um {@link Optional} que pode conter o objeto solicitado
	 */
	public <E> Optional<E> getFromSession(final String key) {
		return (Optional<E>) Optional.of(session.get(key));
	}

	/**
	 * Adiciona uma instância de {@link MessageResult} na execução
	 *
	 * @param message
	 */
	public void addMessage(Message message) {
		this.messages.add(message);
	}

	/**
	 * Cria uma mensagem do tipo informativa no contexto em execução
	 *
	 * @param message
	 */
	public void addInfo(String message) {
		this.messages.add(Message.info(message));
	}

	/**
	 * Cria uma mensagem do tipo alerta/aviso no contexto em execução
	 *
	 * @param message
	 */
	public void addWarn(String message) {
		this.messages.add(Message.warn(message));
	}

	/**
	 * Cria uma mensagem do tipo erro no contexto em execução
	 *
	 * @param message
	 */
	public void addError(String message) {
		this.messages.add(Message.error(message));
	}

	/** @return flag indicando se o contexto de execução possui mensagens */
	public boolean hasMessageResult() {
		return !messages.isEmpty();
	}

	/**
	 * @return Flag informando se a execução do contexto atual foi suspendido <br>
	 *         Uma atividaade pode suspender a execução (impedir que as próximas
	 *         atividades sejam executadas). <br>
	 *         Para parar a execução de uma regra de negócio, basta chamar o método
	 *         {@link #suspendExecution()}
	 */
	public boolean isExecutionSuspended() {
		return executionSuspended;
	}

	/** Para a execução do contexto */
	public void suspendExecution() {
		this.executionSuspended = true;
	}

	/**
	 * @return um {@link Optional} que pode conter um objeto resultado da navegação.
	 *         <br>
	 *         É da responsabilidade das {@link Activity} gerar este objeto e
	 *         informar no contexto usando o método
	 *         {@link #setExecutionResult(Object)}
	 */
	public <E> Optional<E> getExecutionResult() {
		return (Optional<E>) Optional.ofNullable(this.executionResult);
	}

	/**
	 * @param executionResult objeto resultado da execução. Pode ser coletado usando
	 *                        {@link #getExecutionResult()}
	 */
	public void setExecutionResult(Object executionResult) {
		this.executionResult = executionResult;
	}

	/**
	 * @return o objeto navegável na regra de negócio <br>
	 *         O objeto que está sendo trabalhado a regra de negócio
	 */
	public T getNavigableObject() {
		return navigableObject;
	}

	/**
	 * Get messages from execution process context
	 *
	 * @return the value of messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Faz o merge das mensagens de outra execução de regra de negócio
	 * 
	 * @param otherExecutionContext
	 */
	public void mergeMessages(ExecutionContext otherExecutionContext) {
		this.messages.addAll(otherExecutionContext.messages);
	}

	/**
	 * Altera o objeto navegável da execução
	 *
	 * @param navigableObject a value for set
	 */
	public void setNavigableObject(T navigableObject) {
		if (navigableObject == null) {
			throw new RuntimeException("Objeto navegável não pode ser nulo");
		}
		this.navigableObject = navigableObject;
	}
}
