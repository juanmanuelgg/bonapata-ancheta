package ancheta.Matematicas;
public class Complex
{
    // return a string representation of the invoking Complex object
    public static String toString(double[] c)
    {
        if(c[1]==0) return c[0]+"";
        if(c[0]==0) return c[1]+"i";
        if(c[1] <  0) return c[0]+" - "+(-c[1])+"i";
        else return c[0]+" + "+c[1]+"i";
    }

    // return abs/modulus/magnitude and angle/phase/argument
    public static double abs(double[] c)   { return Math.hypot(c[0], c[1]); }  // Math.sqrt(re*re + im*im)
    public static double phase(double[] c) { return Math.atan2(c[1], c[0]); }  // between -pi and pi

    // return a new Complex object whose value is (a + b)
    public static double[] plus(double[] a, double[] b)
    {
        double[] rta={a[0]+b[0], a[1]+b[1]};
        return rta;
    }

    // return a new Complex object whose value is (a - b)
    public static double[] minus(double[] a, double[] b)
    {
        double[] rta={a[0]-b[0], a[1]-b[1]};
        return rta;
    }

    // return a new Complex object whose value is (a * b)
    public static double[] mult(double[] a, double[] b)
    {
        double real = a[0]*b[0] - a[1]*b[1];
        double imag = a[0]*b[1] + a[1]*b[0];
        double[] rta={real, imag};
        return rta;
    }

    // scalar multiplication
    // return a new object whose value is (c * alpha)
    public static double[] mult(double[] c, double alpha)
    {
        double[] rta={c[0]*alpha, c[1]*alpha};
        return rta;
    }

    // return a new Complex object whose value is the conjugate of this
    public static double[] conjugate(double[] c)
    {
        double[] rta={c[0], -c[1]};
        return rta;
    }

    // return a new Complex object whose value is the reciprocal of this
    public static double[] reciprocal(double[] c)
    {
        double scale = c[0]*c[0] + c[1]*c[1];
        double[] rta={c[0]/scale, -c[1]/scale};
        return rta;
    }

    // return the real or imaginary part
    public static double re(double[] c) {return c[0];}
    public static double im(double[] c) {return c[1];}

    // return a / b
    public static double[] divides(double[] a, double[] b)
    {
        return mult(a, reciprocal(b));
    }

    // return a new Complex object whose value is the complex exponential of this
    public static double[] exp(double[] c)
    {
        double[] rta={Math.exp(c[0])*Math.cos(c[1]), Math.exp(c[0])*Math.sin(c[1])};
        return rta;
    }

    // return a new Complex object whose value is the complex sine of this
    public static double[] sin(double[] c)
    {
        double[] rta={Math.sin(c[0])*Math.cosh(c[1]), Math.cos(c[0])*Math.sinh(c[1])};
        return rta;
    }

    // return a new Complex object whose value is the complex cosine of this
    public static double[] cos(double[] c)
    {
        double[] rta={Math.cos(c[0])*Math.cosh(c[1]), -Math.sin(c[0])*Math.sinh(c[1])};
        return rta;
    }

    // return a new Complex object whose value is the complex tangent of this
    public static double[] tan(double[] c)
    {
        return divides(sin(c), cos(c));
    }
}
