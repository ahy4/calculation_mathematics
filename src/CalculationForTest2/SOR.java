package CalculationForTest2;

import CalcUtil.NormType;

/**
 * Created by noko on 2015/12/14.
 */
public class SOR extends IterationalMethods {

    public static double[] SORExecute(double[][] a, double[] b, double[] x, double omega, double eps, int max, ConvergenceWay way, NormType type) {
        return iterationalMethodLoop(a, b, x, eps, max, way, type, (i, x0, x1) -> {
            double _xi = (b[i] - sum_ax(a[i], x1, 0, i) - sum_ax(a[i], x0, i + 1, x0.length)) / a[i][i];
            return (1 - omega) * x0[i] + omega * _xi;
        });
    }

    private static double sum_ax(double[] a_i, double[] x, int start, int end) {
        double sum = 0;
        for (int j = start; j < end; j++) {
            sum += a_i[j] * x[j];
        }
        return sum;
    }

}
