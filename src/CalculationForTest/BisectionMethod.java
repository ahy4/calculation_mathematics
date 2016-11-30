package CalculationForTest;
import java.util.DoubleSummaryStatistics;
import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Created by noko on 2015/07/12.
 */
public class BisectionMethod {
    public static void main(String[] args) {
        BisectionMethod me = new BisectionMethod(
                x -> x * x * x - 3 * x + 2,
                1.0e-12
        );
        me.execute(-10, 0);
    }

    double eps;
    Function<Double, Double> f;

    BisectionMethod(Function<Double, Double> f, double e) {
        this.f = f;
        this.eps = e;
    }

    void execute(double leftBound, double rightBound) {
        double c = (leftBound + rightBound) / 2;
        int count = 0;
        while (abs(rightBound - leftBound) / 2 > eps) {
            count++;
            c = (leftBound + rightBound) / 2;
            if (f.apply(leftBound) * f.apply(c) > 0) {
                leftBound = c;
            } else if (f.apply(leftBound) * f.apply(c) < 0) {
                rightBound = c;
            } else {
                break;
            }
        }

        System.out.println(
                "解: " + c + ",\n" +
                "試行回数: " + count + ",\n" +
                "残差: " + abs(f.apply(c))
        );
    }
}
