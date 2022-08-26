package ancheta.grafo;

import java.util.Iterator;

import ancheta.tablaGrafo.TablaHashG;

import ancheta.exceptions.ElementoRepetidoException;

public class Grafo<K,T> implements IGrafo<T, K>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private TablaHashG<K, IVertice<T>, T> losVertices;
	/**
	 * 
	 */
	private int idActual;
	/**
	 * 
	 */
	public Grafo(){
		idActual=0;
		losVertices= new TablaHashG<K, IVertice<T>, T>();
	}
	/**
	 * 
	 * @return
	 */
	public TablaHashG<K, IVertice<T>, T> getLosVertices() {
		return losVertices;
	}
	/**
	 * 
	 * @return
	 */
	public int getIdActual() {
		return idActual;
	}
	public int getNumeroElementos() {
		return losVertices.darNumElementos();
	}
	/**
	 * 
	 */
	@Override
	public Iterator<IVertice<T>> iterator() {
		return losVertices.iterator();
	}
	/**
	 * @throws ElementoRepetidoException 
	 */
	@Override
	public void agregar(K llave, T elemento) throws ElementoRepetidoException {
		Vertice<T> nuevo = new Vertice<T>(idActual, elemento);
		losVertices.put(llave, nuevo);		
		idActual++;
	}
}
