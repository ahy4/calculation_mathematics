package CalculationForTest2;
import CalcUtil.NormType;


/**
 * Created by noko on 2015/12/13.
 */
public class GaussSeidel extends IterationalMethods {

    public static double[] gaussSeidelExecute(double[][] a, double[] b, double[] x, double eps, int max, ConvergenceWay way, NormType type) {
        return iterationalMethodLoop(a, b, x, eps, max, way, type,
            (i, x0, x1) -> (b[i] - sum_ax(a[i], x1, 0, i) - sum_ax(a[i], x0, i+1, x0.length)) / a[i][i]
        );
    }

    // sum[start->end]a_ij*x_j
    private static double sum_ax(double[] a_i, double[] x, int start, int end) {
        double sum = 0;
        for (int j = start; j < end; j++) {
            sum += a_i[j] * x[j];
        }
        return sum;
    }
}
