package ancheta.grafo;

import java.io.Serializable;

public class Arco<T> implements Comparable<Arco<T>>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int costo;
	private Vertice<T> inicio;
	private Vertice<T> fin;
	private String infoasociada;
	/**
	 * 
	 * @param costoP
	 * @param inicioP
	 * @param finP
	 * @param infoasociadaP
	 */
	public Arco(int costoP, Vertice<T> inicioP, Vertice<T> finP, String infoasociadaP){
		costo=costoP;
		inicio=inicioP;
		fin=finP;
		setInfoasociada(infoasociadaP);
	}
	/**
	 * 
	 * @return
	 */
	public int getCosto() {
		return costo;
	}
	/**
	 * 
	 * @return
	 */
	public Vertice<T> getInicio() {
		return inicio;
	}
	/**
	 * 
	 * @return
	 */
	public Vertice<T> getFin() {
		return fin;
	}
	/**
	 * 
	 * @return
	 */
	public String getInfoasociada() {
		return infoasociada;
	}
	/**
	 * 
	 * @param costo
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}
	/**
	 * 
	 * @param infoasociada
	 */
	public void setInfoasociada(String infoasociada) {
		this.infoasociada = infoasociada;
	}
	/**
	 * 
	 */
	@Override
	public int compareTo(Arco<T> o) {
		return (inicio.equals(o.getInicio())&&fin.equals(o.getFin()))?0:1;
	}
}
