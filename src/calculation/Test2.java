package calculation;

import CalculationForTest.*;

import java.util.function.Function;
// 桁落ち、情報落ち
/**
 * Created by noko on 2015/07/13.
 */
public class Test2 {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) if (isEqual(i)) {
            System.out.println(i);
            System.out.println(HornersRule.horner(1.5, makeNArrayByFunc(func, i)));
            break;
        }
//        System.out.println(fib2(70));
    }

    static Function<Integer, Double> func = n -> { //   1 / n!
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return 1 / fact;
    };

    static double[] makeNArrayByFunc(Function<Integer, Double> f, int len) {
        double[] ary = new double[len];
        for (int i = 0; i < len; i++) {
            ary[len - i - 1] = f.apply(i);
        }

        return ary;
    }

    static Boolean isEqual(int n) { // クソ命名 n番目で等しいかどうか
        return HornersRule.horner(1.5, makeNArrayByFunc(func, n)) == HornersRule.horner(1.5, makeNArrayByFunc(func, n+1));
    }

    static double fib1(int n) { // n >= 1 だけ対応
        double f0 = 0, f1 = 1;
        for (int i = 1; i < n; i++) {
            double tmp = f1;
            f1 = f0 + f1;
            f0 = tmp;
        }
        return f1;
//        return n == 0 || n == 1 ? n : fib(n-1) + fib(n-2);
    }

    static long fib2(int n) { // n >= 1 だけ対応
        long f0 = 0, f1 = 1;
        for (int i = 1; i < n; i++) {
            long tmp = f1;
            f1 = f0 + f1;
            f0 = tmp;
        }
        return f1;
//        return n == 0 || n == 1 ? n : fib(n-1) + fib(n-2);
    }

}
