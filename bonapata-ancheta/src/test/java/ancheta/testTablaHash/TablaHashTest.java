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

package ancheta.testTablaHash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ancheta.comun.ClasePrueba;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.tablaHash.ITablaHash;
import ancheta.tablaHash.TablaHash;

public class TablaHashTest {

	private ITablaHash<String, ClasePrueba> tabla = new TablaHash<String, ClasePrueba>();

	public void setupEscenario1() {

		ClasePrueba lol = new ClasePrueba(0, "Adriana");
		ClasePrueba lol2 = new ClasePrueba(111, "Bernardo");
		ClasePrueba lol3 = new ClasePrueba(47, "Camilo");
		ClasePrueba lol4 = new ClasePrueba(23, "Diana");
		ClasePrueba lol5 = new ClasePrueba(14, "Elena");
		try {
			// se tiene en cuenta para las pruevas de get que 3 elementos se insertaron con
			// la misma llave
			tabla.put(lol.getId() + "", lol);
			tabla.put(lol.getId() + "", lol2);
			tabla.put(lol2.getId() + "", lol2);
			tabla.put(lol3.getId() + "", lol3);
			tabla.put(lol4.getId() + "", lol4);
			tabla.put(lol.getId() + "", lol5);
		} catch (ElementoRepetidoException rep) {
			fail(rep);
		}
	}

	public void setupEscenario1_1() {
		boolean presentoError = false;
		try {
			ClasePrueba lol6 = new ClasePrueba(91, "Francisco");
			tabla.put(lol6.getId() + "", lol6);
			ClasePrueba lol7 = new ClasePrueba(38, "Gloria");
			tabla.put(lol7.getId() + "", lol7);
			ClasePrueba lol8 = new ClasePrueba(68, "Hector");
			tabla.put(lol8.getId() + "", lol8);
			ClasePrueba lol9 = new ClasePrueba(77, "Ines");
			tabla.put(lol9.getId() + "", lol9);
			ClasePrueba lol10 = new ClasePrueba(13, "Juan");
			tabla.put(lol10.getId() + "", lol10);
			ClasePrueba lol11 = new ClasePrueba(53, "Kevin");
			tabla.put(lol11.getId() + "", lol11);
			ClasePrueba lol12 = new ClasePrueba(8, "laura");
			tabla.put(lol12.getId() + "", lol12);
			// existen 12 elementos iterables pero 13 en la tabla
			assertEquals(13, tabla.darCantidadElementosTabla());
		} catch (ElementoRepetidoException e) {
			presentoError = true;
		}
		// no se espera error en esta etapa, es solo una linea de control
		assertEquals(false, presentoError);

	}

	@Test
	public void testPut() {
		// la tabla debe estar vacia
		assertEquals(0, tabla.darCantidadElementosTabla());
		setupEscenario1();
		// ahora deven haber 5 elementos en el iterador pero 6 en la tabla
		assertEquals(5, tabla.darCantidadElementosIterables());
		assertEquals(6, tabla.darCantidadElementosTabla());
		boolean presentoError = false;
		try {
			ClasePrueba lol = new ClasePrueba(0, "Adriana");
			tabla.put(lol.getId() + "", lol);
		} catch (ElementoRepetidoException e) {
			presentoError = true;
		}
		// se agrego un elemento identico a uno que ya esta en la tabla,tanto por llave
		// como por valor, no lo dejo agregar
		assertEquals(true, presentoError);
		// el estado se mantiene
		assertEquals(5, tabla.darCantidadElementosIterables());
		assertEquals(6, tabla.darCantidadElementosTabla());
		// se agregan mas elementos para forzar ala tabla a realizar el rehash
		setupEscenario1_1();
		/*
		 * Iterator<TestClass> iter=tabla.iterator(); int cont=0; while (iter.hasNext())
		 * { TestClass este = (TestClass) iter.next(); cont++;
		 * System.out.println(este+"  "+cont); }
		 */
		// se imprimen los elementos guardados en la tabla y luego se mira las
		// estadisticas de la misma para saber que estan bien
		assertEquals(20, tabla.darLargo());
		assertEquals(12, tabla.darCantidadElementosIterables());
		assertEquals(13, tabla.darCantidadElementosTabla());
	}

	@Test
	public void testGets() {
		setupEscenario1();
		setupEscenario1_1();
		// se cargan los ecenarios de la ves pasada y se espera que presenten las mismas
		// estadisticas
		assertEquals(20, tabla.darLargo());
		assertEquals(12, tabla.darCantidadElementosIterables());
		assertEquals(13, tabla.darCantidadElementosTabla());
		// creamos un obejto y metemos la llave con la que se indexo "Hector"
		ClasePrueba prueva = tabla.get(68 + "");
		assertTrue(prueva.getText().equals("Hector"), "no se rescato el elemento deseado");
		// como salio exitoso para un solo elemento probamos para cuando se indexaron
		// varios con la misma llave
		// Esperamos a "Adriana", "Bernardo" & "Elena"
		Object[] grupo = tabla.getGroup(0 + "");
		for (int i = 0; i < grupo.length; i++) {
			ClasePrueba este = (ClasePrueba) grupo[i];
			try {
				if (i == 0) {
					assertTrue(este.getText().equals("Adriana"),
							"no se rescato el elemento deseado::" + este.getText());
				} else if (i == 1) {
					assertTrue(este.getText().equals("Bernardo"),
							"no se rescato el elemento deseado::" + este.getText());
				} else if (i == 2) {
					assertTrue(este.getText().equals("Elena"), "no se rescato el elemento deseado::" + este.getText());
				}
			} catch (NullPointerException e) {
				// se supone que esto no sucede en este test, es cuando 2 llaves diferentes
				// llegan al mismo espacio
				// es decir se da una colicion, el arreglo de retorno de la tabla lleva huecos
				// vacios pues hace las verificaciones
				// corresponsientes y deja atras a todos los elementos que no coincidan con la
				// llave
				fail(e);
			}
			assertEquals(3, grupo.length);
		}
	}

	@Test
	public void testRemove() {
		setupEscenario1();
		setupEscenario1_1();
		// se cargan los ecenarios de la ves pasada y se espera que presenten las mismas
		// estadisticas
		assertEquals(20, tabla.darLargo());
		assertEquals(12, tabla.darCantidadElementosIterables());
		assertEquals(13, tabla.darCantidadElementosTabla());
		tabla.remove(68 + "");
		assertEquals(12, tabla.darCantidadElementosTabla());
		assertEquals(12, tabla.darCantidadElementosIterables());
		Iterator<ClasePrueba> iter = tabla.iterator();
		int cont = 0;
		while (iter.hasNext()) {
			ClasePrueba este = (ClasePrueba) iter.next();
			cont++;
			System.out.println(este + "  " + cont);
		}
	}
}