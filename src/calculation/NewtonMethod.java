package calculation;


/**
 * Created by yuya on 2015/06/18.
 */
public class NewtonMethod {

    public static void main(String[] args) {
        System.out.println(newtonMethod());
    }

    static double f(double x) {
        return x * x * x + x * x - 5 * x + 3;
    }

    static double fPrime(double x) {
        return 3 * x * x + 2 * x - 5;
    }

    static double newtonMethod() {
        double eps = 1.0e-12;
        double x0 =
            30;
        int i = 1;
        while (Math.abs(f(x0)) >= eps && i <= 50) {
            x0 = x0 - f(x0) / fPrime(x0);
            System.out.println(i + "回の処理で、" + "誤差：" + f(x0) + ", 値：" + x0);
            i++;
        }
        return x0;
    }

    static double secantMethod() {
        double eps = 0.0001;
        double x0 = 2.0, x1 = 1.8;
        double x2;
        while (f(x0) > eps) {
            x2 = x0 - (x1 - x0) * f(x0) / (f(x1) - f(x0));
            x0 = x1;
            x1 = x2;
        }
        return x1;
    }

    static double parallelCodeMethod() {
        double eps = 0.0001;
        double x0 = 2.0;
        double _x = x0;
        while (f(x0) > eps) {
            x0 = x0 - f(x0) / fPrime(_x);
        }
        return x0;
    }

}