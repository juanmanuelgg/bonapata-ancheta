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
