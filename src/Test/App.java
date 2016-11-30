package Test;

import CalcUtil.Matrix;
import CalcUtil.NormType;
import CalcUtil.Vector;
import CalcUtil.Useful.*;
import CalculationForTest2.CholeskeyDecomposition;
import CalculationForTest2.GaussSeidel;
import CalculationForTest2.JacobiSolve;
import CalculationForTest2.NumericalSolution;

import static CalcUtil.Useful.v;

/**
 * Created by noko on 2016/01/11.
 */
public class App {

    public static void execute(double[][] a, double[] b, double[] x) {
        // your algorithm here
        GaussSeidel(a,b,x,1.0e-5,1000,ConvergenceTestMethod.soutaigosa,NormType.TWO);
    }

    
    public static void main(String[] args) {
        timeMeasuring();
    }
    
    public static void timeMeasuring() {
        for (int i = 0; i < 20; i++) {
            double[][] a = makeRandomMatrix(1000);
            double[] x = makeRandomVector(1000);
            double[] b = new Matrix(a).multiply(new Vector(x)).toArray();
            long startTime = System.nanoTime();

            execute(a, b, x);

            long endTime = System.nanoTime();

            long result = endTime - startTime;

            System.out.print(result + " ");

        }
    }

    public static double[][] makeRandomMatrix(int n) {
        double[][] mtx = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = Math.random()*10000;
            }
        }
        return mtx;
    }
    public static double[] makeRandomVector(int n) {
        double[] vec = new double[n];
        for (int i = 0; i < n; i++) {
            vec[i] = Math.random()*10000;
        }
        return vec;
    }
    public static double[][] copyMatrix(double[][] mtx) {
        double[][] copy = new double[mtx.length][mtx[0].length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = copyVector(mtx[i]);
        }
        return copy;
    }
    public static double[] copyVector(double[] ary) {
        double[] copy = new double[ary.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = ary[i];
        }
        return copy;
    }

    public static double[] gaussianElimination(double[][] a, double[] b) {
        forwardminusstitution(a, b);
        backwardElimination(a, b);
        return b;
    }

    public static double[] gaussianEliminationWithPivot(double[][] a, double[] b) throws Exception {
        forwardminusstitutionWithPivot(a, b);
        backwardElimination(a, b);
        return b;
    }

    public static void forwardminusstitution(double[][] a, double[] b) {
        double alpha;
        int n = b.length;
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                alpha = a[i][k] / a[k][k];
                for (int j = k + 1; j < n; j++) {
                    a[i][j] -= a[k][j] * alpha;
                }
                b[i] -= alpha * b[k];
            }
        }
    }

    public static void forwardminusstitutionWithPivot(double[][] a, double[] b) {
        double alpha;
        int n = b.length;
        int l;
        double eps = 10e-18;
        double[] c = new double[a.length + 1];
        for (int k = 0; k < n - 1; k++) {
            l = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(a[i][k]) > Math.abs(a[l][k])) l = i;
            }
            if (Math.abs(a[l][k]) < eps) {
                return;
            }
            if (l != k) {
                for (int i = 0; i < a.length; i++) {
                    double temp = a[l][i];
                    a[l][i] = a[k][i];
                    a[k][i] = temp;
                }
                double tmp = b[k];
                b[k] = b[l];
                b[l] = tmp;
            }
            for (int i = k + 1; i < n; i++) {
                alpha = a[i][k] / a[k][k];
                for (int j = k + 1; j < n; j++) {
                    a[i][j] -= a[k][j] * alpha;
                }
                b[i] -= alpha * b[k];
            }
        }
    }

    public static void backwardElimination(double[][] a, double[] b) {
        int n = b.length;
        for (int k = n - 1; k >= 0; k--) {
            for (int j = k + 1; j < n; j++) {
                b[k] -= a[k][j] * b[j];
            }
            b[k] /= a[k][k];
        }
    }

    public enum ConvergenceTestMethod {
        gosa,
        zansa,
        soutaigosa,
        soutaizansa,
    }

    private static boolean ConvergenceTest(
        double[][] a, double[] b, double[] x1, double[] x0, double eps, ConvergenceTestMethod method, NormType type) {
        switch (method) {
            case gosa:
                if (new Vector(x1).minus(new Vector(x0)).norm(type) < eps)
                    return true;
                break;
            case zansa:
                if (new Vector(b).minus(new Matrix(a).multiply(new Vector(x1))).norm(type) < eps)
                    return true;
                break;
            case soutaigosa:
                if (new Vector(x1).minus(new Vector(x0)).norm(type) / new Vector(x1).norm(type) < eps)
                    return true;
                break;
            case soutaizansa:
                System.out.println(new Vector(b).minus(new Matrix(a).multiply(new Vector(x1))));
                if (new Vector(b).minus(new Matrix(a).multiply(new Vector(x1))).norm(type) / new Vector(b).norm(type) < eps)
                    return true;
                break;
        }
        return false;
    }

    public static double[] jacobi(
        double[][] a, double[] b, double[] x, double eps, int max, ConvergenceTestMethod method, NormType type) {
        double[] lastx = new double[b.length];
        for (int m = 0; m < max; m++) {
            for (int i = 0; i < x.length; i++) {
                double ax = 0;
                for (int j = 0; j < x.length; j++) {
                    if (i != j)
                        ax += a[i][j] * lastx[i];
                }
                x[i] = (b[i] - ax) / a[i][i];
            }
            if (ConvergenceTest(a, b, x, lastx, eps, method, type)) {
                return x;
            }
            for (int i = 0; i < x.length; i++) {
                lastx[i] = x[i];
            }
        }
        return new double[b.length];
    }

    public static double[] GaussSeidel(
        double[][] a, double[] b, double[] x, double eps, int max, ConvergenceTestMethod method, NormType type) {
        double[] lastx = new double[b.length];
        for (int m = 0; m < max; m++) {
            for (int i = 0; i < x.length; i++) {
                double ax = 0;
                for (int j = 0; j < x.length; j++) {
                    if (i != j)
                        ax += a[i][j] * x[i];
                }
                x[i] = 1 / a[i][i] * (b[i] - ax);
            }
            if (ConvergenceTest(a, b, x, lastx, eps, method, type)) {
                return x;
            }
            for (int i = 0; i < x.length; i++) {
                lastx[i] = x[i];
            }
        }
        return new double[b.length];
    }

    public static double[] SOR(
        double[][] a, double[] b, double[] x, double eps, int max, double omega, ConvergenceTestMethod method, NormType type) {
        double[] lastx = new double[b.length];
        for (int m = 0; m < max; m++) {
            for (int i = 0; i < x.length; i++) {
                double ax = 0;
                for (int j = 0; j < x.length; j++) {
                    if (i != j)
                        ax += a[i][j] * x[i];
                }
                x[i] = 1 / a[i][i] * (b[i] - ax);
                x[i] = (1 - omega) * lastx[i] + omega * x[i];
            }
            if (ConvergenceTest(a, b, x, lastx, eps, method, type)) {
                return x;
            }
            for (int i = 0; i < x.length; i++) {
                lastx[i] = x[i];
            }
        }
        return new double[b.length];
    }
}
