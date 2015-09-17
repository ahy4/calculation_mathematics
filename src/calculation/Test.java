package calculation;
import static java.lang.Math.*;

public class Test {

    public static void main(String[] args) {
        double x = 1.0e-9, x2 = 9.999e-10;
        System.out.println((x-x2)/x);
//        for (int i = 1;; i++) if (1.0 + Math.pow(2, -i) == 1.0) {
//            System.out.println(i);
//            break;
//        }
    }


    static double x = 0.9;
//    static double a(int n) {
//        return n * Math.pow(x, n-1);
//    }

//    static double sum(int n) {
//        double result = 0;
//        for (int i = 1; i <= n; i++) {
//            result += a(i);
//        }
//        return result;
//    }

    static double a(int n) {
        if (n == 1) {
            return 51;
        }
        return a(n-1) * x + b(n-1);
    }
    static double b(int n) {
        return 51 - n;
    }

//    static double a(int n) {
//        double fact = 1;
//        for (int i = 1; i <= n; i++) {
//            fact *= i;
//        }
//        return 1.0 / fact;
//    }

}
