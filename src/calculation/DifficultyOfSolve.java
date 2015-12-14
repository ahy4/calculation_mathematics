package calculation;
import CalcUtil.*;
import static CalcUtil.Useful.*;

/**
 * Created by noko on 2015/11/09.
 */
class DifficultyOfSolve {

    static double[][] makeHilbertMatrix(int size) {
        return Calc.matrixMap(new double[size][size], (x, y) -> 1.0 / (x + y - 1));
    }

    // conditionNumber, kappa
    static double cond(double[][] mtx, NormType type) {
        return m(mtx).norm(type) * m(mtx).inverse().norm(type);
    }

    public static void main(String[] args) {
//        System.out.println(kappa(new double[][] {{1,2},{2,3}}));
        System.out.println(m(Calc.inverse(new double[][] {{1,2},{2,3}})));
//        System.out.println(kappa(makeHilbertMatrix(10)));
//        System.out.println(kappa(makeHilbertMatrix(15)));

    }

}
