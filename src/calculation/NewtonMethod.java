package calculation;


/**
 * Created by yuya on 2015/06/18.
 */
public class NewtonMethod {

    public static void main(String[] args) {
        System.out.println(secantMethod());
    }

    static double f(double x) {
        return x * x * x - 3 * x;
    }

    static double fPrime(double x) {
        return 3 * x - 3;
    }

    static double newtonMethod() {
        double eps = 0.0001;
        double x0 = 2.0;
        while (f(x0) > eps) {
            x0 = x0 - f(x0) / fPrime(x0);
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