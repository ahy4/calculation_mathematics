package calculation;

/**
 * Created by yuya on 2015/05/25.
 */

/*
マシンイプシロン
「1.0 + ε_M > 1.0」 が真となる最小の正の数のこと (浮動小数点数)

マシンイプシロンより小さい正の数εについては、
1.0 + ε = 1.0 となる

* 注意 *
マシンイプシロンは浮動小数点数の最小値ではない

(1.0)_10 は
+ 1.00..0 * 2^0 (p = 0, e = 1023)
    52bit
であるから
ε_M = 0.00...01 * 2^0
    = 1.00...00 * 2^-52

倍精度 ε_M = 2^-52 ≒  2.:: * 10^-16
単精度 ε_M = 2^-23 ≒  1.:: * 10^-7


1.0 + 10^-100 = 1.0 (情報落ち)
10^10 + 10^-10 = 10^10

課題
倍精度、単精度のマシンイプシロンを求めよ
* */

public class Lesson0525 {
    public static void main(String[] args) {
//        double x = 1.0;
//        double y = 1.0e-10;
//
//        System.out.println(x + y);
        System.out.println(
            Math.pow(2, -52) + "\n" +
            (float)Math.pow(2, -23)
        );
        System.out.println((float)Math.pow(2, -23) + 1.0f);

        double e = 1.0;
        while (1.0 + e > 1.0) {
            e /= 2;
        } e *= 2;
        System.out.println(e);
    }
}
