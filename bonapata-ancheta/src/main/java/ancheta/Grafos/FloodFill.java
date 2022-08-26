package ancheta.Grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FloodFill
{
	private static final int[] dr ={1,1,0,-1,-1,-1,0,1};//S, SE, E, NE, N, NW, W, SW
	private static final int[] dc ={0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./data/grafos/maze.in")));
		int N=9;
		int M=21;
		
		//Leer dibujo.
		char[][] dibujo=new char[N][M];
		int dj=0, y=0, x=0;
		for(String line; (line=br.readLine())!=null; dj++)
		{
			char[] aux=line.toCharArray();
			for(int i=0; i<M; i++)
			{
				dibujo[dj][i]=aux[i];
				if(dibujo[dj][i]=='*') 
				{
					x=i;
					y=dj;
				}
			}
		}
		br.close();
		
		//Extra: imprimir dibujo.
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<M; j++) System.out.print(dibujo[i][j]+" ");
			System.out.println();
		}
		System.out.println(x+" "+y);
		System.out.println("----------------------------------------------");
		
		//Algoritmo
		dibujo[y][x]=' ';
		int rta=floodFill(dibujo,x,y,' ','#',N,M);
		
		//Extra: imprimir dibujo.
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<M; j++) System.out.print(dibujo[i][j]+" ");
			System.out.println();
		}
		System.out.println(rta);
		System.out.println("----------------------------------------------");

	}

	private static int floodFill(char[][] dibujo, int x, int y, char c1, char c2, int N, int M) //N & M tambien se necesitan
	{
		int rta=0;
		if(x<0 || x>=M || y<0 || y>=N) return 0;
		else
		{
			if(dibujo[y][x]!=c1)return 0;
			else
			{
				dibujo[y][x]=c2;
				rta++;
				for(int d=0; d<8; d++) rta+=floodFill(dibujo, x+dc[d], y+dr[d], c1, c2, N, M);
			}
		}
		return rta;	
	}
}