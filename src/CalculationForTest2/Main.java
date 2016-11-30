package CalculationForTest2;

import CalcUtil.Calc;
import CalcUtil.Matrix;
import CalcUtil.NormType;
import CalcUtil.Vector;

import static CalcUtil.Useful.*;

/**
 * Created by noko on 2015/12/14.
 */
public class Main {
    public static void main(String[] args) {
        double[][] a = {
            {3, 2, 1},
            {1, 4, 1},
            {2, 2, 5}
        };
        double[] b = {10,12,21};
        double[] x = {0, 0, 0};
        double eps = 1.0e-10;
        System.out.println(
            v(JacobiSolve.jacobiExecute(a, b, x, eps, 10000, ConvergenceWay.zansa, NormType.ONE))
        );

        System.out.println(
            v(GaussSeidel.gaussSeidelExecute(a, b, x, eps, 100, ConvergenceWay.zansa, NormType.ONE))
        );

        System.out.println(
            v(SOR.SORExecute(a, b, x, 1.2, eps, 100, ConvergenceWay.zansa, NormType.ONE))
        );

        Matrix mtx = m(
            v(1, -1, 2, 1),
            v(-1, 5, -4, 3),
            v(2, -4, 14, -3),
            v(1, 3, -3, 10)
        );
        Vector v = v(1,1,1,1);
        System.out.println(m(Calc.inverseByCholeskey(mtx.toArray())).multiply(v));

    }

}
