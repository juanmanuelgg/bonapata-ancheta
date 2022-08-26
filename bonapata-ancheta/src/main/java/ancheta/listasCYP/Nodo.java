package ancheta.listasCYP;

import java.io.Serializable;

public class Nodo<T  extends Comparable<T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Nodo<T> siguiente;
	/**
	 * 
	 */
	private T elemento;
	/**
	 * 
	 * @param elementoP
	 */
	public Nodo(T elementoP){
		elemento=elementoP;
		siguiente=null;
	}
	/**
	 * 
	 * @param elementoP
	 */
	public void cambiarElemento(T elementoP) {
		elemento = elementoP;
	}
	/**
	 * 
	 * @return
	 */
	public T darElemento() {
		return elemento;
	}
	/**
	 * 
	 * @param siguienteP
	 */
	public void cambiarSiguiente(Nodo<T> siguienteP) {
		siguiente = siguienteP;
	}
	/**
	 * 
	 * @return
	 */
	public Nodo<T> darSiguiente() {
		return siguiente;
	}
}
