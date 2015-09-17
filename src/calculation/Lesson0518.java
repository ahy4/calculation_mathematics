package calculation;
import static java.lang.Math.*;


/**
 * Created by yuya on 2015/05/18.
 */

/*

浮動小数点数の内部表現

   符号部(1bit)　指数部(8bit)　仮数部(23bit)
単精度(32bit) [[0],[1..8],[9..31]]

   符号部(1bit)　指数部(11bit)　仮数部(52bit)
倍精度(64bit) [[0],[1..11],[9..63]]

0は正,1は負　±(仮数部) * 2(指数部)

(0.1)_10 を２進数にすると、
0.000110011001100....(循環小数)
-> +1.1001100...11001 * 2^(-4)
     ( 52bitに丸める )
*/
public class Lesson0518 {

    public static void main(String[] args) {

//        float a = 0.1f;
//        System.out.printf("a = %.16f\n", a);
//
//        double b = 0.1 + 0.2;
//        System.out.println(b);

        double a = 0.1, b = 0.3, c = 0.4;
        System.out.println((a + b) + c);
        System.out.println(a + (b + c));

        double x = 10e-7;
        System.out.println(problem2Func1(x));
        System.out.println(problem2Func2(x));

        System.out.println (
                problem3_1Func(500) + ", " +
                problem3_1Func(5000) + ", " +
                problem3_1Func(50000) + ", " +
                PI * PI / 6
        );

        System.out.println(comparisonProblem3_2());





    }

    static double problem2Func1(double x) {
        return (1 - cos(x)) / (x*x); // 精度悪い
    }

    static double problem2Func2(double x) {
        // 小数をつかわないように、値の大きくなるように計算すればいいんじゃね？

        // 正しいのは 0.49958347219
        return (10000/x/x - 10000*cos(x)/x/x)/10000;
    }

    static float problem3_1Func(int n) {
        // -> (π^2)/6
        float sum = 0;
        for (int k = 1; k <= n; k++) {
            sum += (float)1/k/k;
        }
        return sum;
    }

    static int comparisonProblem3_2() {
        for (int i = 500; i <= 50000; i++) if (problem3_1Func(i) == problem3_1Func(i+1))
            return i;
        return 0;
    }

}
