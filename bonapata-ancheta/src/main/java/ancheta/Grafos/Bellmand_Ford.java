package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 *
 */
public class Bellmand_Ford
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
		/*
        edgeTo  = new Arco[V];
        boolean[] onQueue=new boolean[V];
        double[] distTo=new double[V];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[SRC]=0.0;

        //Paso 3: Bellman-Ford algorithm
        boolean negativeCicle=bellmand_Ford(adj, onQueue, distTo,  );
        */
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