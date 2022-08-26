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

import java.io.Serializable;

import ancheta.arrayChimbo.ArrayChimbo;
import ancheta.listasCYP.IPilaEncadenada;

public class NodoAVL<V> implements Serializable {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
	private ArrayChimbo<V> losElementos;
	private NodoAVL<V> izquierdo;
	private NodoAVL<V> derecho;
	private NodoAVL<V> padre;
	private String valor;
	private boolean meEstanBalanceando;
	private ArbolAVL<V> arbolPadre;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * 
	 * @param elementoP
	 * @param llaveP
	 * @param nodoPadre
	 * @param arbolPadreP
	 */
	public NodoAVL(V elementoP, String llaveP, NodoAVL<V> nodoPadre, ArbolAVL<V> arbolPadreP) {
		arbolPadre = arbolPadreP;
		valor = llaveP;
		losElementos = new ArrayChimbo<V>();
		losElementos.agregar(elementoP);
		izquierdo = null;
		derecho = null;
		padre = nodoPadre;
		meEstanBalanceando = false;
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	public NodoAVL<V> getIzquierdo() {
		return izquierdo;
	}

	/**
	 * 
	 * @return
	 */
	public NodoAVL<V> getDerecho() {
		return derecho;
	}

	/**
	 * 
	 * @return
	 */
	public NodoAVL<V> getPadre() {
		return padre;
	}

	/**
	 * 
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayChimbo<V> getElementos() {
		return losElementos;
	}

	/**
	 * 
	 * @return
	 */
	public int getTamanioRamaMasLarga() {
		int izq = izquierdo == null ? 1 : izquierdo.getTamanioRamaMasLarga() + 1;
		int der = derecho == null ? 1 : derecho.getTamanioRamaMasLarga() + 1;
		return Math.max(izq, der);
	}

	/**
	 * 
	 * @param izquierdo
	 */
	public void setIzquierdo(NodoAVL<V> izquierdo) {
		this.izquierdo = izquierdo;
	}

	/**
	 * 
	 * @param derecho
	 */
	public void setDerecho(NodoAVL<V> derecho) {
		this.derecho = derecho;
	}

	/**
	 * 
	 * @param padre
	 */
	public void setPadre(NodoAVL<V> padre) {
		this.padre = padre;
	}

	/**
	 * 
	 * @return
	 */
	public boolean esHoja() {
		return (izquierdo == null && derecho == null);
	}

	/**
	 * 
	 * @param elemento
	 * @param llaveP
	 */
	public void insertar(V elemento, String llaveP) {
		NodoAVL<V> nuevo = new NodoAVL<V>(elemento, llaveP, this, arbolPadre);
		if (nuevo.getValor().compareTo(valor) == 0) {
			losElementos.agregar(elemento);
		} else if (nuevo.getValor().compareTo(valor) < 0) {
			if (izquierdo == null) {
				izquierdo = nuevo;
			} else {
				izquierdo.insertar(elemento, llaveP);
			}
		} else if (nuevo.getValor().compareTo(valor) > 0) {
			if (derecho == null) {
				derecho = nuevo;
			} else {
				derecho.insertar(elemento, llaveP);
			}
		}
		balancear();
	}

	/**
	 * 
	 */
	private void balancear() {
		int pesoIzq = izquierdo == null ? 0 : izquierdo.getTamanioRamaMasLarga();
		int pesoDer = derecho == null ? 0 : derecho.getTamanioRamaMasLarga();
		int diferencia = pesoIzq - pesoDer;
		if (diferencia <= -1) {
			// la derecha pesa mas
			if (diferencia < -1) {
				if (hijosEnFormaL()) {
					System.out.println("<D>  BALANCEO DOBLE >>::>>" + valor);
					arbolPadre.aumentarBalanceosDobles();
					meEstanBalanceando = true;
					// Se declara la variable blanco para que los programadores no se vuelen la
					// tusta
					// si se entro a este if entonces estos 3 siempre son diferentes de null
					NodoAVL<V> blanco = this;
					NodoAVL<V> negro = derecho;
					NodoAVL<V> rayas = negro.getIzquierdo();
					// en cambio estos 2 pueden ser nulos
					NodoAVL<V> rojo = rayas.getDerecho();
					NodoAVL<V> azulRey = rayas.getIzquierdo();
					try {
						padre.setHijoEnBalanceoSencillo(rayas);
					} catch (NullPointerException e) {
						arbolPadre.setRaiz(rayas);
					}
					rayas.setPadre(padre);
					rayas.setDerecho(negro);
					negro.setPadre(rayas);
					rayas.setIzquierdo(blanco);
					blanco.setPadre(rayas);
					// dado que rojo y azul rey pueden ser nulos se atrapan posibles null pointer;
					// de manera separarda
					try {
						negro.setIzquierdo(rojo);
						rojo.setPadre(negro);
					} catch (NullPointerException e) {
					}
					try {
						blanco.setDerecho(azulRey);
						azulRey.setPadre(blanco);
					} catch (NullPointerException e) {
					}
					meEstanBalanceando = false;
				} else {
					System.out.println("<D>  BALANCEO SENCILLO ::" + valor);
					meEstanBalanceando = true;
					arbolPadre.aumentarBalanceosSencillos();
					NodoAVL<V> nodoTemp = derecho;
					derecho = derecho.getIzquierdo();
					nodoTemp.setIzquierdo(this);
					try {
						padre.setHijoEnBalanceoSencillo(nodoTemp);
					} catch (NullPointerException ex) {
						arbolPadre.setRaiz(nodoTemp);
					}
					padre = nodoTemp;
					meEstanBalanceando = false;
				}
			}
		} else if (diferencia >= 1) {
			// la izquierda pesa mas
			if (diferencia > 1) {
				if (hijosEnFormaL()) {
					System.out.println("<I>  BALANCEO DOBLE >>::>>" + valor);
					arbolPadre.aumentarBalanceosDobles();
					meEstanBalanceando = true;
					// Se declara la variable blanco para que los programadores no se vuelen la
					// tusta
					// si se entro a este if entonces estos 3 siempre son diferentes de null
					NodoAVL<V> blanco = this;
					NodoAVL<V> negro = izquierdo;
					NodoAVL<V> rayas = negro.getDerecho();
					// en cambio estos 2 pueden ser nulos
					NodoAVL<V> rojo = rayas.getIzquierdo();
					NodoAVL<V> azulRey = rayas.getDerecho();
					try {
						padre.setHijoEnBalanceoSencillo(rayas);
					} catch (NullPointerException e) {
						arbolPadre.setRaiz(rayas);
					}
					rayas.setPadre(padre);
					rayas.setIzquierdo(negro);
					negro.setPadre(rayas);
					rayas.setDerecho(blanco);
					blanco.setPadre(rayas);
					// dado que rojo y azul rey pueden ser nulos se atrapan posibles null pointer;
					// de manera separarda
					try {
						negro.setDerecho(rojo);
						rojo.setPadre(negro);
					} catch (NullPointerException e) {
					}
					try {
						blanco.setIzquierdo(azulRey);
						azulRey.setPadre(blanco);
					} catch (NullPointerException e) {
					}
					meEstanBalanceando = false;
				} else {
					System.out.println("<I>  BALANCEO SENCILLO ::" + valor);
					meEstanBalanceando = true;
					arbolPadre.aumentarBalanceosSencillos();
					NodoAVL<V> nodoTemp = izquierdo;
					izquierdo = izquierdo.getDerecho();
					nodoTemp.setDerecho(this);
					try {
						padre.setHijoEnBalanceoSencillo(nodoTemp);
					} catch (NullPointerException ex) {
						arbolPadre.setRaiz(nodoTemp);
					}
					padre = nodoTemp;
					meEstanBalanceando = false;
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean hijosEnFormaL() {
		boolean rtaDerecha = false;
		boolean rtaIzquierda = false;
		if (izquierdo != null) {
			if (izquierdo.darTamanioRamaDerecha() - izquierdo.darTamanioRamaIzquierda() >= 1) {
				rtaIzquierda = true;
			}
		}
		if (derecho != null) {
			if (derecho.darTamanioRamaIzquierda() - derecho.darTamanioRamaDerecha() >= 1) {
				rtaDerecha = true;
			}
		}
		return (rtaDerecha || rtaIzquierda);
	}

	/**
	 * 
	 * @param nodoTemp
	 */
	private void setHijoEnBalanceoSencillo(NodoAVL<V> nodoTemp) {
		if (derecho.isMeEstanBalanceando()) {
			derecho = nodoTemp;// si se entro a este if entonces estos 3 siempre son diferentes de null
		} else if (izquierdo.isMeEstanBalanceando()) {
			izquierdo = nodoTemp;
		}
	}

	/**
	 * 
	 * @return
	 */
	private int darTamanioRamaDerecha() {
		return derecho == null ? 0 : derecho.getTamanioRamaMasLarga();
	}

	/**
	 * 
	 * @return
	 */
	private int darTamanioRamaIzquierda() {
		return izquierdo == null ? 0 : izquierdo.getTamanioRamaMasLarga();
	}

	/**
	 * 
	 * @param aRetornar
	 */
	// -------------------------------------------
	public void inOrden(IPilaEncadenada<V> aRetornar) {
		if (izquierdo != null) {
			izquierdo.inOrden(aRetornar);
		}
		for (int i = 0; i < losElementos.getSize(); i++) {
			V elem = losElementos.get(0);
			aRetornar.agregarEnPila(elem);
		}
		if (derecho != null) {
			derecho.inOrden(aRetornar);
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isMeEstanBalanceando() {
		return meEstanBalanceando;
	}

	/**
	 * 
	 * @param nivel
	 * @param resp
	 */
	public void darEnNivel(int nivel, IPilaEncadenada<String> resp) {
		if (nivel == 1) {
			resp.agregarEnPila(valor);
		} else {
			if (izquierdo != null) {
				izquierdo.darEnNivel(nivel - 1, resp);
			}
			if (derecho != null) {
				derecho.darEnNivel(nivel - 1, resp);
			}
		}
	}

	/**
	 * 
	 * @param busqueda
	 * @return
	 */
	public ArrayChimbo<V> get(String busqueda) {
		if (busqueda.equals(valor)) {
			return losElementos;
		} else if (busqueda.compareTo(valor) < 0) {
			return izquierdo == null ? null : izquierdo.get(busqueda);
		} else {
			return derecho == null ? null : derecho.get(busqueda);
		}
	}
}
