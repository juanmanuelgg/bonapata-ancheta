package ancheta.testListaDoblementeEncadenada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ancheta.comun.ClasePrueba;
import ancheta.exceptions.ElementoRepetidoException;
import ancheta.listaDoble.Lista2bleEncadenada;

public class TestListaDoble {

	private Lista2bleEncadenada<ClasePrueba> listaD = new Lista2bleEncadenada<ClasePrueba>();

	public void setUpEscenario1() {
		ClasePrueba obj1 = new ClasePrueba(1, "num1");
		ClasePrueba obj2 = new ClasePrueba(1, "num2");
		ClasePrueba obj2_2 = new ClasePrueba(2, "num2");
		ClasePrueba obj3 = new ClasePrueba(3, "num3");
		meterPaLaLista(obj1);
		meterPaLaLista(obj2);
		meterPaLaLista(obj2_2);
		meterPaLaLista(obj3);
	}

	private void meterPaLaLista(Object elemnt) {
		try {
			listaD.agregar((ClasePrueba) elemnt);
		} catch (ElementoRepetidoException e) {
		}
	}

	@Test
	public void testAgregarYBuscar() {
		setUpEscenario1();
		assertEquals(3, listaD.darCantidadDeElementos());
		ClasePrueba mascara = new ClasePrueba(2, null);
		assertEquals("num2", listaD.buscar(mascara).toString());
		assertEquals(3, listaD.darCantidadDeElementos());
	}
}
