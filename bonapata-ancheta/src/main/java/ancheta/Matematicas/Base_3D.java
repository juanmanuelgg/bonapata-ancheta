package ancheta.Matematicas;

import java.util.List;

public class Base_3D
{
	private double movX, movY, movz, tetha_xy, tetha_xz, tetha_yz;

	public Base_3D(double movXP, double movYP, double movZP, double tetha_xyP, double tetha_xzP, double tetha_yzP)
	{
		movX=movXP; movY=movYP; movz=movZP;
		tetha_xy=Math.toRadians(tetha_xyP)%360;
		tetha_xz=Math.toRadians(tetha_xzP)%360;
		tetha_yz=Math.toRadians(tetha_yzP)%360;
	}

	public Base_3D()
	{ movX=movY=movz=tetha_xy=tetha_xz=tetha_yz=0;}

	public void TransformarPunto(double[] po)
	{
		double x=po[0], y=po[1], z=po[2];
		
	}

	public void TransformarPuntos(List<double[]> puntosOriginales)
	{
	}

	public void cambiarParametros(double movXP, double movYP, double movZP, double tetha_xyP, double tetha_xzP, double tetha_yzP)
	{
		movX=movXP; movY=movYP; movz=movZP;
		tetha_xy=Math.toRadians(tetha_xyP)%360;
		tetha_xz=Math.toRadians(tetha_xzP)%360;
		tetha_yz=Math.toRadians(tetha_yzP)%360;
	}
}