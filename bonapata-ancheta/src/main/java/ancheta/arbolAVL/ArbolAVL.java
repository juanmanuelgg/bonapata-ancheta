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

package ancheta.arbolAVL;

import java.util.Iterator;

import ancheta.arrayChimbo.ArrayChimbo;
import ancheta.listasCYP.IPilaEncadenada;
import ancheta.listasCYP.ListaYPila;

public class ArbolAVL<V> implements IArbolAVL<V> {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * Constante de Serializacion
	 */
	private static final long serialVersionUID = 1L;
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	private NodoAVL<V> raiz;
	/**
	 * 
	 */
	private int peso;
	private int balanceosSencillos;
	private int balanceosDobles;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * crea un arbolAvl vacio
	 */
	public ArbolAVL() {
		raiz = null;
		peso = 0;
		balanceosSencillos = 0;
		balanceosDobles = 0;
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------
	@Override
	/**
	 * 
	 */
	public void agregar(V elemento, String llave) {
		NodoAVL<V> nuevo = new NodoAVL<V>(elemento, llave, null, this);
		if (raiz == null) {
			raiz = nuevo;
			peso++;
			System.out.println("inserto: " + peso + "  RAIZ  " + llave);
		} else {
			raiz.insertar(elemento, llave);
			peso++;
			System.out.println("inserto: " + peso + "  NODO  " + llave);
		}
	}

	/**
	 * 
	 */
	@Override
	public int getPeso() {
		return peso;
	}

	/**
	 * 
	 */
	@Override
	public int getAltura() {
		return raiz == null ? 0 : raiz.getTamanioRamaMasLarga();
	}

	/**
	 * 
	 * @return
	 */
	public NodoAVL<V> getRaiz() {
		return raiz;
	}

	/**
	 * 
	 * @param raiz
	 */
	public void setRaiz(NodoAVL<V> raiz) {
		this.raiz = raiz;
	}

	/**
	 * 
	 * @return
	 */
	public int getBalanceosSencillos() {
		return balanceosSencillos;
	}

	/**
	 * 
	 * @return
	 */
	public int getBalanceosDobles() {
		return balanceosDobles;
	}

	// ---------------------------------------
	/**
	 * 
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator<V> iterator() {
		IPilaEncadenada<V> aRetornar = new ListaYPila();
		raiz.inOrden(aRetornar);
		return aRetornar.iterator();
	}

	/**
	 * 
	 */
	@Override
	public void vaciar() {
		raiz = null;
		peso = 0;
		balanceosSencillos = 0;
		balanceosDobles = 0;
	}

	/**
	 * 
	 */
	public void aumentarBalanceosSencillos() {
		balanceosSencillos++;
	}

	/**
	 * 
	 */
	public void aumentarBalanceosDobles() {
		balanceosDobles++;
	}

	/**
	 * 
	 * @param nivel
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IPilaEncadenada<String> darNivel(int nivel) {
		IPilaEncadenada<String> resp = new ListaYPila();
		if (raiz != null && nivel >= 1) {
			raiz.darEnNivel(nivel, resp);
		}
		return resp;
	}

	// ----------------------------------------
	/**
	 * 
	 */
	@Override
	public ArrayChimbo<V> getGroup(String busqueda) {
		return raiz == null ? null : raiz.get(busqueda) == null ? new ArrayChimbo<V>() : raiz.get(busqueda);
	}
}