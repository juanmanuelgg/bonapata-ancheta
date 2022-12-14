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

package ancheta.tablaGrafo;

import java.util.Iterator;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.grafo.IVertice;
import ancheta.arrayChimbo.ArrayChimbo;

public class TablaHashG<K, V extends IVertice<T>, T> implements ItablaHashG<K, IVertice<T>, T> {

	private static final long serialVersionUID = 1L;

	private int numeroElem;

	private ArrayChimbo<PaqueteHash<K, IVertice<T>>> copia;

	private ListaHashG<K, PaqueteHash<K, IVertice<T>>>[] areaPrimaria;

	private int tamanioTabla;

	@SuppressWarnings("unchecked")
	public TablaHashG() {
		numeroElem = 0;
		tamanioTabla = 10;
		areaPrimaria = new ListaHashG[tamanioTabla];
		copia = new ArrayChimbo<PaqueteHash<K, IVertice<T>>>();
	}

	@Override
	public void put(K llave, IVertice<T> elem) throws ElementoRepetidoException {
		int pos = funcionHash(llave);
		if (numeroElem <= tamanioTabla * .75) {
			rehash();
			pos = funcionHash(llave);
		}
		if (areaPrimaria[pos] == null) {
			areaPrimaria[pos] = new ListaHashG<K, PaqueteHash<K, IVertice<T>>>();
		}
		PaqueteHash<K, IVertice<T>> paquete = new PaqueteHash<K, IVertice<T>>(llave, elem);
		areaPrimaria[pos].agregar(paquete, llave);
		copia.agregar(paquete);
		numeroElem++;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		tamanioTabla = tamanioTabla * 2;
		numeroElem = 0;
		areaPrimaria = new ListaHashG[tamanioTabla];
		Iterator<PaqueteHash<K, IVertice<T>>> iter = copia.iterator();
		while (iter.hasNext()) {
			PaqueteHash<K, ancheta.grafo.IVertice<T>> estePaquete = (PaqueteHash<K, ancheta.grafo.IVertice<T>>) iter
					.next();
			int pos = funcionHash(estePaquete.getLlave());
			if (areaPrimaria[pos] == null) {
				areaPrimaria[pos] = new ListaHashG<K, PaqueteHash<K, IVertice<T>>>();
			}
			PaqueteHash<K, IVertice<T>> paquete = new PaqueteHash<K, IVertice<T>>(estePaquete.getLlave(),
					estePaquete.getElemento());
			try {
				areaPrimaria[pos].agregar(paquete, estePaquete.getLlave());
				numeroElem++;
			} catch (ElementoRepetidoException e) {
				System.out.println("ERROR SUPER HYPER MEGA FATAL--- remitace a Tabla hash G");
				e.printStackTrace();
			}
		}
	}

	private int funcionHash(K llave) {
		return llave.hashCode() % tamanioTabla;
	}

	@Override
	public IVertice<T> get(K llave) {
		int pos = funcionHash(llave);
		ListaHashG<K, PaqueteHash<K, IVertice<T>>> encontrado = areaPrimaria[pos];
		Iterator<PaqueteHash<K, IVertice<T>>> iter = encontrado.iterator();
		while (iter.hasNext()) {
			PaqueteHash<K, ancheta.grafo.IVertice<T>> este = (PaqueteHash<K, ancheta.grafo.IVertice<T>>) iter.next();
			if (este.getLlave().equals(llave)) {
				return este.getElemento();
			}
		}
		return null;
	}

	@Override
	public IVertice<T> eliminar(K llave) {
		int pos = funcionHash(llave);
		ListaHashG<K, PaqueteHash<K, IVertice<T>>> encontrado = areaPrimaria[pos];
		Iterator<PaqueteHash<K, IVertice<T>>> iter = encontrado.iterator();
		int i = 0;
		while (iter.hasNext()) {
			PaqueteHash<K, ancheta.grafo.IVertice<T>> este = (PaqueteHash<K, ancheta.grafo.IVertice<T>>) iter.next();
			if (este.getLlave().equals(llave)) {
				copia.remove(i);
			}
			i++;
		}
		IVertice<T> resp = encontrado.eliminar(llave).getElemento();
		if (resp != null) {
			numeroElem--;
		}
		return resp;
	}

	@Override
	public int darNumElementos() {
		return numeroElem;
	}

	@Override
	public Iterator<IVertice<T>> iterator() {
		ArrayChimbo<IVertice<T>> resp = new ArrayChimbo<IVertice<T>>();
		Iterator<PaqueteHash<K, IVertice<T>>> iter = copia.iterator();
		while (iter.hasNext()) {
			PaqueteHash<K, ancheta.grafo.IVertice<T>> estePAque = iter.next();
			resp.agregar(estePAque.getElemento());
		}
		return resp.iterator();
	}

	public ArrayChimbo<PaqueteHash<K, IVertice<T>>> getCopia() {
		return copia;
	}

	public ListaHashG<K, PaqueteHash<K, IVertice<T>>>[] getAreaPrimaria() {
		return areaPrimaria;
	}

}
