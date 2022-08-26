package ancheta.exceptions;

public class palabraInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String invalida; 
	/**
	 * 
	 * @param palabra
	 */
	public palabraInvalidaException(String palabra)
	{
		invalida=palabra;
	}
	/**
	 * 
	 */
	@Override
	public String getMessage() {
		return (invalida.length()<=3)?"La palabra es Muy corta ::: "+invalida:"La palabra es invalida ::: "+invalida;
	}
}
