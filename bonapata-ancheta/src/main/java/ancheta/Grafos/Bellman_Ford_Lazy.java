package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
 *
 */
public class Bellman_Ford_Lazy
{
	private static final int SRC=0;

	public static void main(String[] args) throws IOException
	{
		BufferedReader  br=new BufferedReader(new FileReader(new File("./data/grafos/SP.in")));

		int V, E, from, to;
		double peso;

		String aux[]=br.readLine().split(" ");
		V=Integer.parseInt(aux[0]);
		E=Integer.parseInt(aux[1]);

		//Paso 1: Representar el grafo como una lista de adjacenica de Arcos
		@SuppressWarnings("unchecked")
		List<Arco>[] adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<Arco>();
		while(E--!=0)
		{
			aux=br.readLine().split(" ");
			from=Integer.parseInt(aux[0]);
			to=Integer.parseInt(aux[1]);
			peso=Double.parseDouble(aux[2]);

			adj[from].add(new Arco(to, peso));
		}
		br.close();

		//Paso 2: Prepara las estructura de datos donde guardo la RTA.
		double[] distTo=new double[V];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		int[] edgeTo=new int[V];

		bellman_Ford(adj, edgeTo, distTo, V);

		boolean existeCicloNegativo=hayCicloNegativo(adj, distTo, V);
		System.out.printf("Hay ciclo: %b\n",existeCicloNegativo);

		for(int i=0; i<V; i++)
		{
			System.out.printf("%d to %d (%.2f) :: ", SRC, i, distTo[i]);

			if(!existeCicloNegativo)
			{
				Stack<Integer> camino=pathTo(i, edgeTo, distTo);
				StringBuilder sb=new StringBuilder();
				while(!camino.isEmpty()) sb.append(camino.pop()+" ");
				System.out.println(sb.toString());
			}
			else System.out.println();
		}
	}

	private static void bellman_Ford(List<Arco>[] adj, int[] edgeTo, double[] distTo, int V)
	{
		distTo[SRC]=0;

		for(int numRelajaciones=V-1 ,v ,w; numRelajaciones--!=0;)
		{
			for(v=0; v<V; v++)
			{
				for(Arco e: adj[v])
				{
					w=e.to;
					if(distTo[w]>distTo[v]+e.peso)
					{
						distTo[w]=distTo[v]+e.peso;
						edgeTo[w]=v;
					}
				}
			}
		}
	}

	private static Stack<Integer> pathTo(int v, int[] edgeTo, double[] distTo)
	{
		Stack<Integer> path=new Stack<Integer>();
		if(distTo[v]==Double.POSITIVE_INFINITY || distTo[v]<0) return path;


		int x;
		for(x=v; distTo[x]!=0; x=edgeTo[x])path.push(x);
		path.push(x);

		return path;
	}

	private static boolean hayCicloNegativo(List<Arco>[] adj, double[] distTo, int V)
	{
		boolean hayCiclo=false;
		revision: for(int v=0; v<V; v++)
		{
			for(Arco e: adj[v])
			{
				if(distTo[e.to]>distTo[v]+e.peso)
				{
					hayCiclo=true;
					break revision;
				}
			}
		}
		return hayCiclo;
	}

	private static class Arco
	{
		int to; double peso;

		public Arco(int toP, double pesoP)
		{to=toP; peso=pesoP;}

		public String toString()
		{return String.format("[%d (%.2f)]", to, peso);}
	}
}