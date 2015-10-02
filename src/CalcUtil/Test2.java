package CalcUtil;

/**
 * Created by noko on 2015/09/30.
 */
public class Test2 {
    public static void main(String[] args) {
        double[] vector = { 1,2,3 };
        System.out.println(MyCalc.vecNorm1(vector));
        Complex[] vector2 = {
            new Complex(1, 3),
            new Complex(5, -1),
            new Complex(9)
        };
        System.out.println(MyCalc.vecNorm1(vector2));
    }
}

class MyCalc {

    static double vecNorm1(double[] ary) {
        double sum = 0;
        for (int i = 0; i < ary.length; i++) {
            sum += Math.abs(ary[i]);
        }
        return sum;
    }
    static double vecNorm1(Complex[] ary) {
        double sum = 0;
        for (int i = 0; i < ary.length; i++) {
            sum += ary[i].abs();
        }
        return sum;
    }

}

class Complex {
    double rationalPart;
    double imaginalPart;

    Complex(double r, double i) {
        rationalPart = r;
        imaginalPart = i;
    }
    Complex(double r) {
        this(r, 0);
    }

    double abs() {
        return Math.sqrt(
            rationalPart*rationalPart + imaginalPart*imaginalPart
        );
    }
}