package ancheta.grafo;

import ancheta.listasCYP.ListaYPila;

public class Vertice<T>implements IVertice<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ListaYPila<Arco<T>> predecesores;
	/**
	 * 
	 */
	private ListaYPila<Arco<T>> sucesores;
	/**
	 * 
	 */
	private int id;
	/**
	 * 
	 */
	private boolean marcado;
	/**
	 * 
	 */
	private T elemento;
	/**
	 * 
	 * @param idP
	 * @param elementoP
	 */
	public Vertice(int idP, T elementoP){
		id = idP;
		elemento=elementoP;
		marcado=false;
	}
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return
	 */
	public T getElemento() {
		return elemento;
	}
	/**
	 * 
	 * @return
	 */
	public ListaYPila<Arco<T>> getPredecesores() {
		return predecesores;
	}
	/**
	 * 
	 * @return
	 */
	public ListaYPila<Arco<T>> getSucesores() {
		return sucesores;
	}
	/*public void setId(int id) {
		this.id = id;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}*/
	/**
	 * 
	 */
	@Override
	public int compareTo(Vertice<T> o) {
		return elemento.equals(o.getElemento())?0:1;
	}
	public String toString()
	{
		return elemento.toString();
	}
	public boolean isMarcado() {
		return marcado;
	}
	public void setMarcado(boolean marcadoP) {
		marcado = marcadoP;
	}
}
