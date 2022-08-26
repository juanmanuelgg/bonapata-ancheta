package ancheta.listaDoble;

import java.io.Serializable;

public interface IlistaDoble<T> extends Serializable {

	/**
	 * 
	 * @return
	 */
	public T darActualEnUso();
	/**
	 * 
	 * @return
	 */
	public T darSiguienteElemento();
	/**
	 * 
	 * @return
	 */
	public T darAnteriorElemento();

}
