package calculation_again;

/**
 * Created by noko on 2016/06/02.
 */
public class Main0602 {
    public static void main(String[] args) {
        newton();
        secant();
        parallelChord();
    }
    static void newton() {
        double x0 = -6;
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
    }
    static void secant() {
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
    static void parallelChord() {
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
        return x*x*x-4*x*x+13.0/4*x-3.0/4;
    }
    static double df(double x) {
        return 3*x*x-8*x+13.0/4;
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
        return residualDiff(x1) < 1.0e-10 || itr >= 300;
    }
}
