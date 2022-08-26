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
import java.util.Stack;

public class Kosaraju
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./data/grafos/SCC.in")));

		int V, E, from, to;
		
		String[] aux=br.readLine().split(" ");
		V=Integer.parseInt(aux[0]);
		E=Integer.parseInt(aux[1]);

		//Paso 1: Representar el grafo como una lista de adjacenica de Arcos y su inverso
		@SuppressWarnings("unchecked")
		List<Integer>[] adj=new ArrayList[V], inv=new ArrayList[V];
		for(int i=0; i<V; i++) 
		{
			adj[i]=new ArrayList<>();
			inv[i]=new ArrayList<>();
		}
		while(E--!=0)
		{
			aux=br.readLine().split(" ");
			from=Integer.parseInt(aux[0]);
			to=Integer.parseInt(aux[1]);

			adj[from].add(to); inv[to].add(from);
		}
		br.close();

		//Paso 2: Prepara las estructura de datos donde guardo la RTA.
		int[] id=new int[V];
		
        //Paso 3: Kosaraju
		int color=kosaraju(adj, inv, id, V);
		
		System.out.println(color);
		System.out.println(Arrays.toString(id));
	}

	private static int kosaraju(List<Integer>[] adj, List<Integer>[] inv, int[] id, int V)
	{
		//Paso 1: obtener el reverse-post del grafo invertido, mandar un dfs por cada nodo sin visitar
		Stack<Integer> reversePost = new Stack<Integer>();
		boolean[] marcado= new boolean[V];
		for(int v=0; v<V; v++) if(!marcado[v]) dfs_1(inv, reversePost, marcado, v);

		//Paso 2: Hacer un DfS en el orden del "reverse-post"
		int color=0, s;
		while(!reversePost.isEmpty()) 
		{
			s=reversePost.pop();
			if(id[s]==0) {color++;bfs_2(adj, id, s, color);}
		}
		return color;
	}

	private static void dfs_1(List<Integer>[] inv, Stack<Integer> reversePost, boolean[] marcado, int v)
	{
		marcado[v]=true;
		for(int w : inv[v]) if(!marcado[w]) dfs_1(inv, reversePost, marcado, w);
		reversePost.push(v);
	}

	private static void bfs_2(List<Integer>[] adj, int[] id, int s, int color)
	{
		Queue<Integer> q=new LinkedList<Integer>();
		id[s]=color;
		q.add(s);

		while(!q.isEmpty())
		{
			int v=q.poll();
			for(int w: adj[v])
			{
				if(id[w]==0)
				{
					id[w]=color;
					q.add(w);
				}
			}
		}
	}
}