package ancheta.listaDoble;

import java.io.Serializable;

public class NodoDoble<T  extends Comparable<? super T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private NodoDoble<T> siguiente;
	/**
	 * 
	 */
	private NodoDoble<T> anterior;
	/**
	 * 
	 */
	private T elemento;
	/**
	 * 
	 * @param elementoP
	 */
	public NodoDoble(T elementoP){
		elemento=elementoP;
		siguiente=null;
		anterior=null;
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
	public void cambiarSiguiente(NodoDoble<T> siguienteP) {
		siguiente = siguienteP;
	}
	/**
	 * 
	 * @return
	 */
	public NodoDoble<T> darSiguiente() {
		return siguiente;
	}
	/**
	 * 
	 * @param anterior
	 */
	public void cambiarAnterior(NodoDoble<T> anterior) {
		this.anterior = anterior;
	}
	/**
	 * 
	 * @return
	 */
	public NodoDoble<T> darAnterior() {
		return anterior;
	}

}
