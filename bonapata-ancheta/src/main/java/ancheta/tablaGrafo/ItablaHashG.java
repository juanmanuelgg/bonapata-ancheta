package ancheta.tablaGrafo;

import java.io.Serializable;

import ancheta.exceptions.ElementoRepetidoException;
import ancheta.grafo.IVertice;

public interface ItablaHashG<K,V extends IVertice<T>, T> extends Serializable,Iterable<V>{
	
	public void put(K llave, V elem) throws ElementoRepetidoException;
	public V get (K llave);
	public V eliminar(K llave);
	public int darNumElementos();
}
