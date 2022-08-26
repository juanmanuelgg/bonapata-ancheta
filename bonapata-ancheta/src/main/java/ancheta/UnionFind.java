package ancheta;

public class UnionFind
{
	private int[] idPadres;
	private int[] cantHijos;
	private int cantConjuntos;
	
	public UnionFind(int N)
	{
		cantConjuntos=N;
		
		idPadres=new int[N];
		cantHijos=new int[N];
		for(int i=0; i<N; i++)
		{
			idPadres[i]=i;
			cantHijos[i]=1;
		}
	}
	
	public int getCount() {return cantConjuntos;}
	
	//Retorna la cantida de elementos que resultaron unidos.
	public int union(int p, int q)
	{
		int i=root(p);
		int j=root(q);
		if(i!=j)
		{
			idPadres[i]=j;
			cantHijos[j]+=cantHijos[i];
			cantConjuntos--;
		}
		return cantHijos[j];
	}
	
	public boolean conected(int p, int q)
	{
		return root(p)==root(q);
	}

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

	//Para cuando se buscan componenetes conexos
	public int[] darRoots()
	{
		int[] rta=new int[idPadres.length];
		for(int i=0; i<rta.length; i++) rta[i]=root(i);
		return rta;
	}
}
