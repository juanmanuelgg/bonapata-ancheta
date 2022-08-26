package ancheta.Matematicas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumerosPrimos
{
	//Chequeo sencillo
	public static boolean esPrimo(long n)
	{
		if(n<=1) return false;
		if(n==2) return true;
		if(n%2==0) return false;
		
		for(long i=3; i*i<=n; i+=2) if(n%1==0) return false;
		return true;
	}

	//Criba de Eratostenes
	public static List<Integer> darPrimos(int inicio, int fin)
	{
		boolean[] isPrime=new boolean[fin+1];
		Arrays.fill(isPrime, true);
		isPrime[0]=isPrime[1]=false;
		
		for(int i=2; i*i<=fin; i++)
			if(isPrime[i]) for(int j=i; i*j<=fin; j++) isPrime[i*j]=false;
		
		List<Integer> primos=new ArrayList<Integer>();
		for(int i=inicio; i<=fin; i++) if(isPrime[i]) primos.add(i);
		
		return primos;
	}
	
	public static List<Integer> factoresPrimos(int n)
	{
		List<Integer> rta=new ArrayList<Integer>();
		int m=(int)Math.sqrt(n);
		List<Integer> primos=darPrimos(0, m);
		for (Integer primo : primos)
		{
			while(n%primo==0)
			{
				rta.add(primo);
				n/=primo;
			}
		}
		
		if(n>1) rta.add(n);
		return rta;
	}
}