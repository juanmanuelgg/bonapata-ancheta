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

import ancheta.listasCYP.ListaYPila;

public class Vertice<T> implements IVertice<T> {

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
	public Vertice(int idP, T elementoP) {
		id = idP;
		elemento = elementoP;
		marcado = false;
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

	/*
	 * public void setId(int id) { this.id = id; } public void setElemento(T
	 * elemento) { this.elemento = elemento; }
	 */
	/**
	 * 
	 */
	@Override
	public int compareTo(Vertice<T> o) {
		return elemento.equals(o.getElemento()) ? 0 : 1;
	}

	public String toString() {
		return elemento.toString();
	}

	public boolean isMarcado() {
		return marcado;
	}

	public void setMarcado(boolean marcadoP) {
		marcado = marcadoP;
	}
}
