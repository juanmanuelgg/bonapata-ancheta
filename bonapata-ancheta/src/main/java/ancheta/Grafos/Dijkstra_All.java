package ancheta.Grafos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Dijkstra_All con PriorityQueue
 * Complejidad Temporal: O(V*(E+V)*Log(V))
 * Complejidad Espacial: O(V^2)
 */
public class Dijkstra_All
{
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

			adj[from].add(new Arco(from, to, peso));
		}
		br.close();

		//Paso 2 Prepara las estructura de datos donde guardo la RTA.
		Arco[] edgeTo=new Arco[V];
		double[] distTo=new double[V];

		//Paso 3: Dijkstra V Veces
		double[][] mat=new double[V][V];
		for(int i=0; i<V; i++)
		{
			Arrays.fill(edgeTo, null);
			Arrays.fill(distTo, Double.POSITIVE_INFINITY);

			dijkstra(adj,edgeTo,distTo, i);
			mat[i]=distTo.clone();
		}

		for(int i=0; i<V; i++)
		{
			for(int j=0; j<V; j++) System.out.printf("%.2f ",mat[i][j]);
			System.out.println();
		}
		System.out.println("---------------------------------------");

//		Stack<Integer> camino=printPath(0, 6, path);
//		StringBuilder sb=new StringBuilder();
//		while(!camino.isEmpty()) sb.append(camino.pop()+" ");
//		System.out.println("0 to 6: "+sb.toString());
	}

	public static void dijkstra(List<Arco>[] adj, Arco[] edgeTo, double[] distTo, int src)
	{
		PriorityQueue<NodoDijkstra>pq=new PriorityQueue<NodoDijkstra>();

		distTo[src]=0.0;
		pq.add(new NodoDijkstra(src, 0.0));
		for(int v, w; !pq.isEmpty();)
		{
			NodoDijkstra aRelajar=pq.poll();
			v=aRelajar.id;
			for(Arco edge: adj[v]) //Relajar el nodo
			{
				w=edge.to;
				if(distTo[w]>distTo[v]+edge.peso)
				{
					distTo[w]=distTo[v]+edge.peso;
					edgeTo[w]=edge;
					pq.remove(aRelajar);
					pq.add(new NodoDijkstra(w, distTo[w]));
				}
			}
		}
	}

	public static Stack<Arco> pathTo(int v, double[] distTo, Arco[] edgeTo)
	{
		if (distTo[v]==Double.POSITIVE_INFINITY) return null;

		Stack<Arco> path = new Stack<Arco>();
		for(Arco e=edgeTo[v]; e!=null; e=edgeTo[e.from]) path.push(e);
		return path;
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
		{return String.format("[%d->%d (%.2f)]", from, to, peso);}
	}

	private static class NodoDijkstra implements Comparable<NodoDijkstra>
	{
		int id; double dist;
		public NodoDijkstra(int wP, double distP)
		{id=wP; dist=distP;}

		@Override
		public int compareTo(NodoDijkstra arg0)
		{return Double.compare(dist, arg0.dist);}

		public String toString()
		{return String.format("%d (%.2f)", id, dist);}
	}
}