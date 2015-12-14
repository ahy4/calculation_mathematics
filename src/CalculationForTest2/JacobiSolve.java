package CalculationForTest2;

import CalcUtil.NormType;

/**
 * Created by noko on 2015/12/13.
 */
public class JacobiSolve extends IterationalMethods {

    public static double[] jacobiExecute(double[][] a, double[] b, double[] x, double eps, int max, ConvergenceWay way, NormType type) {
        return iterationalMethodLoop(a, b, x, eps, max, way, type,
            (i, x0, x1) -> (b[i] - getSum(a[i], x0, i)) / a[i][i]
        );

    }

    private static double getSum(double[] a_i, double[] x, int i) {
        double sum = 0;
        for (int j = 0; j < x.length; j++) if (i != j) {
                sum += a_i[j] * x[j];
        }
        return sum;
    }
}
