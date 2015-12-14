package CalculationForTest2;
import static CalcUtil.Useful.*;
import CalcUtil.NormType;

/**
 * Created by noko on 2015/12/14.
 */
public class LinearEquationTest {

    double[][] a;
    double[] b;
    double[] x;
    double omega = 1.2;
    double eps = 10e-12;
    int max = 100;
    NumericalSolution.ConvergenceWay way = NumericalSolution.ConvergenceWay.soutaigosa;
    NormType type = NormType.ONE;

    public LinearEquationTest(double[][] a, double[] b) {
        a = a;
        b = b;
        x = v(b).map((v, i) -> 0.0).toArray();
    }

//    public LinearEquationTest setOtherProps(double[] x, double eps, double max, double omega, NumericalSolution.ConvergenceWay way, NormType type) {
//        x = x;
//        eps = eps;
//    }

}
