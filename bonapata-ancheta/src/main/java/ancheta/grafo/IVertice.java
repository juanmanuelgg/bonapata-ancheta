package ancheta.grafo;

import java.io.Serializable;

public interface IVertice<T> extends Serializable, Comparable<Vertice<T>> {

	public int getId() ;
	
}
