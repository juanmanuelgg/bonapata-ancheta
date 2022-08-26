package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Dfs
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./data/grafos/Grafo.in")));
		//INIT: Crear Grafo
		int N=Integer.parseInt(br.readLine());
		@SuppressWarnings("unchecked")
		List<Integer>[] adj=new ArrayList[N];
		for(int i=0;i<N; i++) adj[i]=new ArrayList<Integer>();
		
		//INIT: Cargar Grafo
		int from, to;
		for(String line, aux[];(line=br.readLine())!=null;)
		{
			aux=line.split(" ");
			from=Integer.parseInt(aux[0]);
			to=Integer.parseInt(aux[1]);
			adj[from].add(to);
			//adj[to].add(from); sin direccion
		}
		br.close();
		
		//EXTRA: Imprimir Grafo
		for(int i=0; i<N; i++) System.out.println(i+": "+adj[i]);
		System.out.println("-----------------------------------");
		
		//INIT: inicializar las estructuras de datos.
		int[] marcados=new int[N];
		Arrays.fill(marcados, -1);
		int[] edgeTo=new int[N];
		
		//Correr Dfs en todos los nodos
		for(int i=0; i<N; i++) if(marcados[i]==-1) dfs(i, adj, marcados, edgeTo,i);
		
		//Imprimir infopracion resultante
		for(int i=0; i<N; i++)
		{
			System.out.printf("Nodo: %d, componente:%d, camino: ", i, marcados[i]);
			System.out.println(pathTo(i, marcados, edgeTo));
		}
	}
	
	private static void dfs(int source, List<Integer>[] adj, int[] marcados, int[] edgeTo, int v)
	{
		marcados[v]=source;
		for(int w: adj[v])
		{
			if(marcados[w]!=source)
			{
				edgeTo[w]=v;
				dfs(source, adj, marcados, edgeTo, w);
			}
		}
	}
	
	private static Iterable<Integer> pathTo(int v, int[] marcados, int[] edgeTo)
	{
		if(marcados[v]==-1) return null;
		
		Stack<Integer> path=new Stack<Integer>();
		
		int x;
		for(x=v; x!=marcados[x]; x=edgeTo[x]) path.push(x);
		path.push(marcados[x]);
		
		return path;
	}
}