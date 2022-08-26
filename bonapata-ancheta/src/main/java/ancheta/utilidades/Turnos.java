package ancheta.utilidades;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Turnos {

	/**
	 * 
	 */
	public static ArrayList<Integer> alTablero() throws NumberFormatException, IOException {
	
		ArrayList<Integer> estudiantes= new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Total Estudiantes: ");
		int totalEstudiantes = Integer.parseInt(br.readLine());
		System.out.print("pasan Tablero: ");
		int cuantos = Integer.parseInt(br.readLine());  
		
		for (int i = 0; i < cuantos; i++) 
		{
			Double rdm= Math.random();
			int resp=(int) (rdm*totalEstudiantes);
			estudiantes.add(resp+1);
		}
		br.close();
		return estudiantes;
	}
}
