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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.comun.ClasePrueba;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.listasCYP.ListaYPila;

public class ListaTest {

	private ListaYPila<ClasePrueba> lista = new ListaYPila<ClasePrueba>();

	public void setupEscenario1() {

		ClasePrueba lol = new ClasePrueba(0, "Adriana");
		meterPaLaLista(lol);
		ClasePrueba lol2 = new ClasePrueba(111, "Bernardo");
		meterPaLaLista(lol2);
		ClasePrueba lol3 = new ClasePrueba(47, "Camilo");
		meterPaLaLista(lol3);
		ClasePrueba lol4 = new ClasePrueba(23, "Diana");
		meterPaLaLista(lol4);
		ClasePrueba lol5 = new ClasePrueba(14, "Elena");
		meterPaLaLista(lol5);
	}

	private void meterPaLaLista(Object elemnt) {
		try {
			lista.agregar((ClasePrueba) elemnt);
		} catch (ElementoRepetidoException e) {
			fail(e);
		}
	}

	@Test
	public void testMetodosVaciado() {
		assertEquals(true, lista.esVacia());
		assertEquals(0, lista.darCantidadDeElementos());
		setupEscenario1();
		assertEquals(5, lista.darCantidadDeElementos());
		assertEquals(false, lista.esVacia());
		lista.vaciar();
		assertEquals(0, lista.darCantidadDeElementos());
		assertEquals(true, lista.esVacia());
	}

	@Test
	public void testAgregar() {
		setupEscenario1();
		assertEquals(5, lista.darCantidadDeElementos());
		Object[] versionArreglo = lista.darEnArreglo();
		for (int i = 0; i < versionArreglo.length; i++) {
			ClasePrueba xxx = (ClasePrueba) versionArreglo[i];
			if (i == 0) {
				assertEquals("Adriana", xxx.getText());
			} else if (i == 1) {
				assertEquals("Bernardo", xxx.getText());
			} else if (i == 2) {
				assertEquals("Camilo", xxx.getText());
			} else if (i == 3) {
				assertEquals("Diana", xxx.getText());
			} else if (i == 4) {
				assertEquals("Elena", xxx.getText());
			}
		}
	}

	@Test
	public void testEliminar() {
		setupEscenario1();
		ClasePrueba t = new ClasePrueba(23, null);
		assertNotNull(lista.eliminar(t), "no elimino");
		assertEquals(4, lista.darCantidadDeElementos());
		Object[] versionArreglo = lista.darEnArreglo();
		for (int i = 0; i < versionArreglo.length; i++) {
			ClasePrueba xxx = (ClasePrueba) versionArreglo[i];
			if (i == 0) {
				assertEquals("Adriana", xxx.getText());
			} else if (i == 1) {
				assertEquals("Bernardo", xxx.getText());
			} else if (i == 2) {
				assertEquals("Camilo", xxx.getText());
			} else if (i == 3) {
				assertEquals("Elena", xxx.getText());
			}
		}
		t = new ClasePrueba(14, null);
		assertNotNull(lista.eliminar(t), "no elimino");
		assertEquals(3, lista.darCantidadDeElementos());
	}

	@Test
	public void testCopiaOrdenada() {
		setupEscenario1();
		Object[] acendente = lista.sort();
		for (int i = 0; i < acendente.length; i++) {
			ClasePrueba xxx = (ClasePrueba) acendente[i];
			if (i == 0) {
				assertEquals("Adriana", xxx.getText());
			} else if (i == 1) {
				assertEquals("Elena", xxx.getText());
			} else if (i == 2) {
				assertEquals("Diana", xxx.getText());
			} else if (i == 3) {
				assertEquals("Camilo", xxx.getText());
			} else if (i == 4) {
				assertEquals("Bernardo", xxx.getText());
			}
		}
		Object[] decendente = lista.sort();
		int j = 0;
		for (int i = decendente.length - 1; i >= 0; i--) {
			ClasePrueba xxx = (ClasePrueba) decendente[i];
			if (j == 0) {
				assertEquals("Bernardo", xxx.getText());
			} else if (j == 1) {
				assertEquals("Camilo", xxx.getText());
			} else if (j == 2) {
				assertEquals("Diana", xxx.getText());
			} else if (j == 3) {
				assertEquals("Elena", xxx.getText());
			} else if (j == 4) {
				assertEquals("Adriana", xxx.getText());
			}
			j++;
		}
	}

	@Test
	public void testEdicionElementos() {
		setupEscenario1();
		Iterator<ClasePrueba> iter = lista.iterator();
		while (iter.hasNext()) {
			ClasePrueba esta = (ClasePrueba) iter.next();
			if (esta.getText().equals("Camilo")) {
				esta.setText("Modificado");
			}
		}
		Object[] versionArreglo = lista.darEnArreglo();
		for (int i = 0; i < versionArreglo.length; i++) {
			ClasePrueba xxx = (ClasePrueba) versionArreglo[i];
			if (i == 2) {
				assertEquals("Modificado", xxx.getText());
			}
		}
	}
}
