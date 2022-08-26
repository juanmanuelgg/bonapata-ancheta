package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Kruskal
 * Complejidad Temporal: O(E*Log(E))
 * Complejidad Espacial: O(E)
 */
public class Kruskal
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./TestData/MST.in")));
		
		int V, E, from, to;
		double peso;
		
		String aux[]=br.readLine().split(" ");
		V=Integer.parseInt(aux[0]);
		E=Integer.parseInt(aux[1]);
		
		//Paso 1: Representar el grafo como una lista de arcos ordenada
		PriorityQueue<Arco> listaDeArcos=new PriorityQueue<Arco>(E);
		while(E--!=0)
		{
			aux=br.readLine().split(" ");
			from=Integer.parseInt(aux[0]);
			to=Integer.parseInt(aux[1]);
			peso=Double.parseDouble(aux[2]);
			
			listaDeArcos.add(new Arco(from, to, peso));
		}
		br.close();
		
		//Paso 2: Kruskal
		List<Arco> mst=kruskal(listaDeArcos,V);
		System.out.println(mst);
	}
	
	private static List<Arco> kruskal(PriorityQueue<Arco> listaDeArcos, int V)
	{
		List<Arco> mst=new ArrayList<Arco>();
		UnionFind uf=new UnionFind(V);
		
		while(!listaDeArcos.isEmpty() && mst.size()<V-1)
		{
			Arco e=listaDeArcos.poll();
			if(!uf.conected(e.from, e.to))
			{
				uf.union(e.from, e.to);
				mst.add(e);
			}
		}
		return mst;
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
	
	public static class UnionFind
	{
		private int[] idPadres;//private int cantConjuntos;
		
		public UnionFind(int N)
		{//cantConjuntos=N;
			idPadres=new int[N];
			for(int i=0; i<N; i++) idPadres[i]=i;
		}
		//public int getCount() {return cantConjuntos;}
		public void union(int p, int q)
		{
			int i=root(p);
			int j=root(q);
			if(i!=j) idPadres[i]=j; //(dentro del if)cantConjuntos--;
		}
		
		public boolean conected(int p, int q) { return root(p)==root(q);}

		private int root(int i)
		{
			int root=i;
			while(root!=idPadres[root]) root=idPadres[root];
			while(i!=root)
			{
				int newp=idPadres[i];
				idPadres[i]=root;
				i=newp;
			}
			return root;
		}
	}
}
