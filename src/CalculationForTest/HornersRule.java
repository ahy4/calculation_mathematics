package CalculationForTest;

/**
 * Created by noko on 2015/07/12.
 */
public class HornersRule {
    public static void main(String[] args) {
        double x = 0.4;
        double[] an = { 16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1 }; // 係数の数列
        System.out.println(
            HornersRule.horner(x, an)
            // f(x) = 50*x + 40*x^2 + 30*x^3 + 20*x^4 + 10*x^5 を計算する
        );
    }

    // a[0]*x^n + a[1]*x^(n-1) + ... + a[n-2]*x^2 + a[n-1]*x^1 + a[n]*x^0 を計算する
    public static double horner(double x, double[] a) {
        double y = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            y = y * x + a[i];
            count+=2;
        }
        System.out.println(count);
        return y;
    }
}


