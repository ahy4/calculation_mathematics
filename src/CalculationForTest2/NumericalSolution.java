package CalculationForTest2;
import static CalcUtil.Useful.*;
import CalcUtil.NormType;

/**
 * Created by noko on 2015/12/13.
 */
public class NumericalSolution {
    public static double[][] makeRandomMatrix(int n) {
        double[][] mtx = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = Math.random();
            }
        }
        return mtx;
    }
    public static double[] makeRandomVector(int n) {
        double[] vec = new double[n];
        for (int i = 0; i < n; i++) {
            vec[i] = Math.random();
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

    // conditionNumber, kappa
    static double cond(double[][] mtx, NormType type) {
        return m(mtx).norm(type) * m(mtx).inverse().norm(type);
    }


    public enum ConvergenceWay {
        gosa,
        zansa,
        soutaigosa,
        soutaizansa,
    }

    public static boolean convergenceTest(double[][] a, double[] b, double[] x1, double[] x0, double eps, ConvergenceWay method, NormType type) {
        switch (method) {
            case gosa:
                if (v(x1).minus(v(x0)).norm(type) < eps)
                    return true;
                break;
            case zansa:
                if (v(b).minus(m(a).multiply(v(x1))).norm(type) < eps)
                    return true;
                break;
            case soutaigosa:
                if (v(x1).minus(v(x0)).norm(type) / v(x1).norm(type) < eps)
                    return true;
                break;
            case soutaizansa:
                if (v(b).minus(m(a).multiply(v(x1))).norm(type) / v(b).norm(type) < eps)
                    return true;
                break;
        }
        return false;
    }

}
