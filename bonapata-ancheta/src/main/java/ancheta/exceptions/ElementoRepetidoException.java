package ancheta.exceptions;

public class ElementoRepetidoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Object objetoRepetido;
	/**
	 * 
	 * @param elemento
	 */
	public ElementoRepetidoException(Object elemento)
	{
		objetoRepetido=elemento;
	}
	/**
	 * 
	 */
	@Override
	public String getMessage() {
		return "Este elemento ya esta en la lista: "+objetoRepetido.toString();
	}

}
