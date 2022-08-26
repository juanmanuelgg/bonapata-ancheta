package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Floyd_Warshall
{
	//private static int INF=1000000000; si los pesos son enteros
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./data/grafos/SP.in")));

		//Paso 1: Representar el grafo como una matriz de adjacenica.
		int V, E, from, to;
		double peso;

		String aux[]=br.readLine().split(" ");
		V=Integer.parseInt(aux[0]);
		E=Integer.parseInt(aux[1]);

		double[][] mat=new double[V][V];
		for(int i=0;i<V; i++)
			for(int j=0; j<V; j++) if(i!=j) mat[i][j]=Double.POSITIVE_INFINITY;

		while(E--!=0)
		{
			aux=br.readLine().split(" ");
			from=Integer.parseInt(aux[0]);
			to=Integer.parseInt(aux[1]);
			peso=Double.parseDouble(aux[2]);
			mat[from][to]=peso;
			//mat[to][from]=peso; sin direccion
		}
		br.close();

		//Paso 2 Prepara las estructura de datos donde guardo la RTA.
		double[][] pesos=mat.clone();
		int[][] path=new int[V][V];
		for(int i=0; i<V; i++)
			for(int j=0; j<V; j++) path[i][j]=i;

		//Paso 3: Floyd_Warshall
		floyd_Warshall(V, mat, path);

		for(int i=0; i<V; i++)
		{
			for(int j=0; j<V; j++) System.out.printf("%.2f ",mat[i][j]);
			System.out.println();
		}

		System.out.println("---------------------------------------");
		double costo=0; final int fuente=0, destino=6;
		
		Stack<Integer> camino=printPath(fuente, destino, path);
		StringBuilder sb=new StringBuilder();
		for(int anterior=fuente, actual; !camino.isEmpty();)
		{
			actual=camino.pop();
			costo+=pesos[anterior][actual];
			sb.append(actual+" ");
			anterior=actual;
		}//El costo ya se conoce es lo que encontamos en mat[fuente][destino], esta forma es alternativa.
		System.out.printf("0 to 6: %s :: Costo(%.2f)",sb.toString(), costo);
	}

	private static void floyd_Warshall(int V, double[][] mat, int[][] path)
	{
		for(int k=0; k<V; k++)
		{
			for(int i=0; i<V; i++)
			{
				for(int j=0; j<V; j++)
				{
					if(mat[i][k]+mat[k][j] < mat[i][j])
					{
						mat[i][j]=mat[i][k]+mat[k][j];
						path[i][j]=path[k][j];
					}
				}
			}
		}
	}

	private static Stack<Integer> printPath(int i, int j, int[][] path)
	{
		Stack<Integer> stack=new Stack<Integer>();
		while(i!=j)
		{
			stack.push(j);
			j=path[i][j];
		}
		stack.push(j);

		return stack;
	}
}