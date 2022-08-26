/*
 * MIT License
 * 
 * Copyright (c) 2022 Juan Manuel González Garzón
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ancheta.grafo;

import java.io.Serializable;

public class Arco<T> implements Comparable<Arco<T>>, Serializable {

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
	public Arco(int costoP, Vertice<T> inicioP, Vertice<T> finP, String infoasociadaP) {
		costo = costoP;
		inicio = inicioP;
		fin = finP;
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
		return (inicio.equals(o.getInicio()) && fin.equals(o.getFin())) ? 0 : 1;
	}
}
