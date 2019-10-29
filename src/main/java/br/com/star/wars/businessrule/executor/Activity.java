package br.com.star.wars.businessrule.executor;

public interface Activity<T> {
 void execute(ExecutionContext<T> context) throws Exception;
}
