package ancheta.utilidades;

import java.io.IOException;
import java.util.ArrayList;

public class PruevasStrings {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("----[[Start]]----");
		ArrayList<Integer> pasanHoy = null;
		try {
			pasanHoy = Turnos.alTablero();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < pasanHoy.size(); i++) {
			System.out.println(pasanHoy.get(i));
		}
		System.out.println("-----[[fin]]-----");
	}
}
