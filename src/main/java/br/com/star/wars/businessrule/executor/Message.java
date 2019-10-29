package br.com.star.wars.businessrule.executor;

public class Message {
	private Level level;
	private String message;
	
	 /**
	   * Cria uma instancia de {@link MessageResult} do tipo erro
	   * @param message
	   * @return
	   */
	  public static Message error(final String message) {
	      return create(Level.ERROR, message);
	  }
	  
	  /**
	   * Cria uma instancia de {@link MessageResult} do tipo alerta/aviso
	   * @param message
	   * @return
	   */
	  public static Message warn(final String message) {
	      return create(Level.WARN, message);
	  }

	  /**
	   * Cria uma instancia de {@link MessageResult} do tipo informativa
	   * @param message
	   * @return
	   */
	  public static Message info(final String message) {
	    return create(Level.INFO, message);
	  }

	  /**
	   * Cria uma instancia de {@link MessageResult} informando a severidade e a mensagem
	   *
	   * @param level
	   * @param message
	   * @return
	   */
	  public static Message create(final Level level, final String message) {
	    Message newMessage  = new Message();
	    newMessage.level = level;
	    newMessage.message = message;
	    return newMessage;
	  }
	  
	  /**
	   * Flag if is a informative message
	   * @return
	   */
	  public boolean isInfo() {
	    return Level.INFO.equals(level);
	  }
	  
	  /**
	   * Flag if is a warning message
	   * @return
	   */
	  public boolean isWarn() {
	    return Level.WARN.equals(level);
	  }
	  
	  /**
	   * Flag if is a error message
	   * @return
	   */
	  public boolean isError() {
	    return Level.ERROR.equals(level);
	  }
	  
	  /**
	   * Get {@link Level} from message
	   *
	   * @return the value of level
	   */
	  public Level getLevel() {
	    return level;
	  }

	  /**
	   * Get the value of message. The content message
	   *
	   * @return the value of message
	   */
	  public String content() {
	    return message;
	  }
}
