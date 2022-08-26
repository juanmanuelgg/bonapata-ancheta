package ancheta.Matematicas;

public class Base_2D
{
	private double movX, movY, tetha;

	public Base_2D(double movXP, double movYP, double tethaP)
	{movX=movXP; movY=movYP; tetha=Math.toRadians(tethaP%360);}

	public Base_2D()
	{movX=movY=tetha=0;}

	public void TransformarPunto(double[] po)
	{
		double x=po[0], y=po[1];
		po[0]= x*Math.cos(tetha)+y*Math.sin(tetha)+movX;
		po[1]=-x*Math.sin(tetha)+y*Math.cos(tetha)+movY;
	}

	public double[] TransformarPuntos(double[][] listaPuntos)
	{
		double[] centros=new double[2];
		double x,y;
		for(double[] ds: listaPuntos)
		{
			x=ds[0]; y=ds[1];
			centros[0]+=x; centros[1]+=y;
			ds[0]= x*Math.cos(tetha)+y*Math.sin(tetha)+movX;
			ds[1]=-x*Math.sin(tetha)+y*Math.cos(tetha)+movY;
		}
		centros[0]/=listaPuntos.length; centros[1]/=listaPuntos.length;
		return centros;
	}

	public void cambiarParametros(double movXP, double movYP, double tethaP)
	{movX=movXP; movY=movYP; tetha=Math.toRadians(tethaP%360);}
}