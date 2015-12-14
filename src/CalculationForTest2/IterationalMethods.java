package CalculationForTest2;
import CalcUtil.NormType;

/**
 * Created by noko on 2015/12/13.
 */
public class IterationalMethods extends NumericalSolution {
    public static double[] iterationalMethodLoop(double[][] a, double[] b, double[] x, double eps, int max, ConvergenceWay way, NormType type,
                                                 RecursiveEquation equation) {
        double[] y = copyVector(x);
        for (int m = 0; m < max; m++) {
            double[] currentY = copyVector(y);
            for (int i = 0; i < y.length; i++) {
                y[i] = equation.apply(i, currentY, y);
            }
            if (convergenceTest(a, b, y, currentY, eps, way, type)) {
                return y;
            }
        }
        System.out.println("収束しなかった");
        return y;
    }

    public interface RecursiveEquation {
        double apply(int i, double[] x0, double[] x1);
    }
}
