package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Mejor implementacion de Prim
 * Complejidad Temporal: O(E*log(V))
 * Complejidad Espacial: O(V)
 */
public class Prim_Mejor
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./data/grafos/MST.in")));

		int V, E, from, to;
		double peso;

		String aux[]=br.readLine().split(" ");
		V=Integer.parseInt(aux[0]);
		E=Integer.parseInt(aux[1]);

		//Paso 1: Representar el grafo como lista de adjacencias
		@SuppressWarnings("unchecked")
		List<Arco>[] adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<Arco>();
		
		while(E--!=0)
		{
			aux=br.readLine().split(" ");
			from=Integer.parseInt(aux[0]);
			to=Integer.parseInt(aux[1]);
			peso=Double.parseDouble(aux[2]);
			
			adj[from].add(new Arco(from, to, peso));
			adj[to].add(new Arco(to, from, peso));
		}
		br.close();
		
		//Paso 2: incializar estructuras de datos.
		Arco[] edgeTo=new Arco[V];
		boolean[] marcado=new boolean[V];
		double[] distTo=new double[V];
		for(int i=0; i<V; i++) distTo[i]=1000000000;
		PriorityQueue<NodoPrim> pq=new PriorityQueue<NodoPrim>();
		
		prim(adj, edgeTo, marcado, distTo, pq);
		
		System.out.println(Arrays.toString(edgeTo));
	}

	private static void prim(List<Arco>[] adj, Arco[] edgeTo, boolean[] marcado, double[] distTo, PriorityQueue<NodoPrim> pq)
	{
		distTo[0]=0.0;
		pq.add(new NodoPrim(0, 0.0));
		
		for(int v, w; !pq.isEmpty();)
		{
			NodoPrim aRelajar=pq.poll();
			
			v=aRelajar.id;
			marcado[v]=true;
			for(Arco e: adj[v]) //Relajar nodo
			{
				w=e.to;
				if(!marcado[w])
				{
					if(e.peso < distTo[w])
					{
						edgeTo[w]=e;
						distTo[w]=e.peso;
						pq.remove(aRelajar);
						pq.add(new NodoPrim(w, distTo[w]));
					}
				}
			}
		}
	}

	private static class Arco implements Comparable<Arco>
	{
		int from, to; double peso;
		
		public Arco(int fromP, int toP, double pesoP)
		{from=fromP; to=toP; peso=pesoP;}
		
		@Override
		public int compareTo(Arco arg0)
		{return Double.compare(peso, arg0.peso);}
		
		public String toString()
		{return String.format("%d-%d (%.2f)", from, to, peso);}
	}
	
	private static class NodoPrim implements Comparable<NodoPrim>
	{
		int id; double dist;
		public NodoPrim(int wP, double distP)
		{id=wP; dist=distP;}
		
		@Override
		public int compareTo(NodoPrim arg0)
		{return Double.compare(dist, arg0.dist);}
		
		public String toString()
		{return String.format("%d (%.2f)", id, dist);}
	}
}