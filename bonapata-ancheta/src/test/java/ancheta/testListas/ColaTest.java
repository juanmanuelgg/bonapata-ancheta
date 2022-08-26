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

package ancheta.testListas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.comun.ClasePrueba;
import ancheta.listasCYP.IColaEncadenada;
import ancheta.listasCYP.ListaYCola;

public class ColaTest {

	private IColaEncadenada<ClasePrueba> cola = new ListaYCola<ClasePrueba>();

	public void setUpEscenario1_1() {
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num1");
		cola.agregarEnCola(aInsertar1);
	}

	public void setUpEscenario1_2() {

		ClasePrueba aInsertar2 = new ClasePrueba(2, "num2");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num3");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num4");
		ClasePrueba aInsertar5 = new ClasePrueba(5, "num5");
		ClasePrueba aInsertar6 = new ClasePrueba(6, "num6");
		ClasePrueba aInsertar7 = new ClasePrueba(7, "num7");

		cola.agregarEnCola(aInsertar2);
		cola.agregarEnCola(aInsertar3);
		cola.agregarEnCola(aInsertar4);
		cola.agregarEnCola(aInsertar5);
		cola.agregarEnCola(aInsertar6);
		// se manda un elemento repetido
		cola.agregarEnCola(aInsertar5);
		cola.agregarEnCola(aInsertar7);
	}

	public void setUpEscenario2() {
		ClasePrueba aInsertar1 = new ClasePrueba(1, "num1");
		ClasePrueba aInsertar2 = new ClasePrueba(2, "num2");
		ClasePrueba aInsertar3 = new ClasePrueba(3, "num3");
		ClasePrueba aInsertar4 = new ClasePrueba(4, "num4");
		cola.agregarEnCola(aInsertar1);
		cola.agregarEnCola(aInsertar2);
		cola.agregarEnCola(aInsertar3);
		// se manda un elemento repetido
		cola.agregarEnCola(aInsertar2);
		cola.agregarEnCola(aInsertar4);
	}

	/**
	 * Prueva el comportamiento de la cola esperando que se comporte como una
	 * Cola(Estructura de datos)
	 */
	@Test
	public void testCola() {
		// se evalua el comportamiento de la cola sin elementos
		assertEquals(0, cola.darCantidadDeElementos());
		assertNull(cola.tomarElemento());
		// se evalua el comportamiento con solo un elemento
		setUpEscenario1_1();
		assertEquals(1, cola.darCantidadDeElementos());
		assertEquals("num1", cola.tomarElemento().getText());
		// se evalua para un conjunto demayor tamaño
		setUpEscenario1_1();
		setUpEscenario1_2();
		assertEquals(8, cola.darCantidadDeElementos());
		assertEquals("num7", cola.tomarElemento().getText());
		assertEquals(7, cola.darCantidadDeElementos());
		assertEquals("num5", cola.tomarElemento().getText());
		assertEquals(6, cola.darCantidadDeElementos());
		assertEquals("num6", cola.tomarElemento().getText());
		assertEquals(5, cola.darCantidadDeElementos());
		assertEquals("num5", cola.tomarElemento().getText());
		assertEquals(4, cola.darCantidadDeElementos());
		assertEquals("num4", cola.tomarElemento().getText());
		assertEquals(3, cola.darCantidadDeElementos());
		assertEquals("num3", cola.tomarElemento().getText());
		assertEquals(2, cola.darCantidadDeElementos());
		assertEquals("num2", cola.tomarElemento().getText());
		assertEquals(1, cola.darCantidadDeElementos());
		assertEquals("num1", cola.tomarElemento().getText());
		assertEquals(0, cola.darCantidadDeElementos());
		assertNull(cola.tomarElemento());
		assertEquals(0, cola.darCantidadDeElementos());
		assertNull(cola.tomarElemento());
	}

	@Test
	public void testIterador() {
		// se evalua el comportamiento de la cola sin elementos
		assertEquals(0, cola.darCantidadDeElementos());
		assertNull(cola.tomarElemento());
		// se carga para menos casos
		setUpEscenario2();
		assertEquals(5, cola.darCantidadDeElementos());
		Iterator<ClasePrueba> iter = cola.iterator();
		int i = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			if (i == 0) {
				assertEquals("num4", este.getText());
			} else if (i == 1) {
				assertEquals("num2", este.getText());
			} else if (i == 2) {
				assertEquals("num3", este.getText());
			} else if (i == 3) {
				assertEquals("num2", este.getText());
			} else if (i == 4) {
				assertEquals("num1", este.getText());
			}
			i++;
		}
		assertEquals(5, cola.darCantidadDeElementos());
	}

}
