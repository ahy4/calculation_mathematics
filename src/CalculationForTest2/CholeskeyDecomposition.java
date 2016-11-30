package CalculationForTest2;

import CalcUtil.Calc;

import java.util.function.Function;

/**
 * Created by noko on 2015/12/14.
 */
public class CholeskeyDecomposition {

    // 正定値対称行列に限る

    // [1,1],[2,1],[3,1],...,[n,1],[2,2],[3,2],..の順番を守らなければいけない
    public static double[][] exec(double[][] mtx) {
        double[][] l = new double[mtx.length][mtx[0].length];
        for (int j = 0; j < mtx[0].length; j++) for (int i = j; i < mtx.length; i++) {
            l[i][j] = l(i, j, mtx);
        }
        return l; // LUでいうUは、L^Tである
    }

    private static double l(int i, int j, double[][] a) {
        return i == j
            ? Math.sqrt(a[i][i] - Calc.sum((k) -> l(i, k, a), 0, i))
            : (a[i][j] - Calc.sum((k) -> l(i, k, a) * l(j, k, a), 0, j)) / l(j, j, a);
    }
    public static void dummy() {
        return;
    }


}
