package CalculationAgainForTest;

/**
 * Created by noko on 2016/07/06.
 */
public class SolveAlgorithm
{
    public static void newton() {
        double x0 = -20;
        double x1 = x0 - f(x0) / df(x0);
        int i;
        for (i = 0; !exitCondition(x0, x1, i); i++) {
            System.out.println("x" + i + " = " + x0);
            x0 = x1;
            x1 = x0 - f(x0) / df(x0);
        }
        System.out.println("x" + i + " = " + x0);
        System.out.println("x" + (i+1) + " = " + x1);
        System.out.println("反復回数:"+(i+1));
        System.out.println(absoluteDiff(x1,0.5));
        System.out.println(df(3));
    }
    public static void secant() {
        double x0 = 6;
        double x1 = x0 + 1.0;
        double x2 = scfunc(x0,x1);
        int i;
        for (i = 0; !exitCondition(x0, x1, i); i++) {
            System.out.println("x" + i + " = " + x0);
            x0 = x1;
            x1 = x2;
            x2 = scfunc(x0,x1);
        }
        System.out.println("x" + i + " = " + x0);
        System.out.println("x" + (i+1) + " = " + x1);
        System.out.println("反復回数:"+(i+1));
        System.out.println(absoluteDiff(3,x1));
    }
    static double scfunc(double x0, double x1) {
        return x1 - f(x1) * (x0 - x1) / (f(x0) - f(x1));
    }
    public static void parallelChord() {
        double x0 = 6;
        double x1 = x0 - f(x0) / df(x0);
        int i;
        double slope = df(x0);
        for (i = 0; !exitCondition(x0, x1, i); i++) {
            System.out.println("x" + i + " = " + x0);
            x0 = x1;
            x1 = x0 - f(x0) / slope;
        }
        System.out.println("x" + i + " = " + x0);
        System.out.println("x" + (i+1) + " = " + x1);
        System.out.println("反復回数:"+(i+1));
        System.out.println(absoluteDiff(3,x1));
    }
    static double f(double x) {
        return p(x,5) - 6*p(x,4) + 6*p(x,3) + 20*x*x - 39*x + 18;
    }
    static double df(double x) {
        return 5*p(x,4) - 24*p(x,3) + 18*p(x,2) + 40*x - 39;
    }
    static double absoluteDiff(double a, double b) {
        return Math.abs(a - b);
    }
    static double relativeDiff(double a, double b) {
        return Math.abs((a - b) / a);
    }
    static double residualDiff(double a) {
        return Math.abs(f(a));
    }
    static boolean exitCondition(double x0, double x1, int itr) {
        return relativeDiff(3, x1) < 1.0e-8 || itr >= 200;
    }


    static double p(double x, double n){return Math.pow(x,n);}

}
