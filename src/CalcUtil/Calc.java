package CalcUtil;

import CalculationForTest2.CholeskeyDecomposition;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static CalcUtil.Useful.m;
import static CalcUtil.Useful.v;
import static java.lang.Math.*;


public class Calc {

    public static void printVec(double[] ary) {
        Arrays.stream(ary).forEach((v) -> {
            System.out.print(v + " ");
        });
        System.out.println();
    }

    public static void printMat(double[][] mat) {
        Arrays.stream(mat).forEach((ary) -> {
            Arrays.stream(ary).forEach((v) -> {
                System.out.print(v + " ");
            });
            System.out.println();
        });
    }

    public static double[] scalarMultiple(double c, double[] ary) {
        return Arrays.stream(ary)
            .map((v) -> c * v)
            .toArray();
    }

    public static double[] addVec(double[] ary1, double[] ary2) {
        return IntStream.range(0, ary1.length)
            .mapToDouble((i) -> ary1[i] + ary2[i])
            .toArray();
    }

    public static double[] subVec(double[] ary1, double[] ary2) {
        return addVec(ary1, negative(ary2));
    }
    public static double[] negative(double[] ary) {
        return scalarMultiple(-1, ary);
    }

    public static double innProd(double[] ary1, double[] ary2) {
        return IntStream
            .range(0, ary1.length)
            .mapToDouble((i) -> ary1[i] * ary2[i])
            .sum();
    }

    public static double[] matVec(double[][] mat, double[] ary) {
        return IntStream
            .range(0, mat.length)
            .mapToDouble((i) -> innProd(mat[i], ary))
            .toArray();
    }

    public static double[][] addMat(double[][] mat1, double[][] mat2) {
        return matrixMap(mat1, (x, y) -> (double) x + y);
    }

    public static double[][] scalarMultiple(double c, double[][] mat) {
        return matrixMap(mat, (x, y) -> c * mat[x][y]);
    }
    public static double[][] multipleMat(double[][] mat1, double[][] mat2) {
        double[][] mat2t = matTransposition(mat2);
        return twoDimMap(mat1.length, mat2t.length, (x, y) -> innProd(mat1[x], mat2t[y]));
    }

    public static double[][] twoDimMap(int x, int y, BiFunction<Integer, Integer, Double> callback) {
        double[][] result = new double[x][y];
        IntStream.range(0, x).forEach((_x) -> {
            IntStream.range(0, y).forEach((_y) -> {
                result[_x][_y] = callback.apply(_x, _y);
            });
        });
        return result;
    }
    public static double[][] matrixMap(double[][] mtx, BiFunction<Integer, Integer, Double> callback) {
        return twoDimMap(mtx.length, mtx[0].length, callback);
    }

    public static double vecNorm1(double[] ary) {
        return Arrays.stream(ary)
            .map(Math::abs)
            .sum();
    }

    public static double vecNorm2(double[] ary) {
        return Math.sqrt(
            IntStream
                .range(0, ary.length)
                .mapToDouble((i) -> ary[i] * ary[i])
                .sum()
        );
    }

    public static double vecNormInf(double[] ary) {
        return Arrays.stream(ary)
            .map(Math::abs)
            .max()
            .getAsDouble();
    }

    public static double matNorm1(double[][] mat) {
        return matNorm1(matTransposition(mat));
    }

    public static double matNormInf(double[][] mat) {
        return vecNormInf(
            IntStream.range(0, mat.length)
                .mapToDouble((i) -> vecNorm1(mat[i]))
                .toArray()
        );
    }
    public static double matNormFrobenius(double[][] mtx) {
        return Math.sqrt(
            IntStream.range(0, mtx.length)
                .mapToDouble(i -> Math.pow(vecNorm2(mtx[i]), 2))
                .sum()
        );
    }

    public static double[][] matTransposition(double[][] mat) {
        return twoDimMap(mat[0].length, mat.length, (x, y) -> mat[y][x]);
    }


    public static double[][] inverse(double[][] mtx) {
        int n = mtx.length;
        double[][] e = new double[n][n];
        for (int i = 0; i < n; i++) {
            e[i][i] = 1;
        }
        double[][] y = new double[n][n];
        double[][] x = new double[n][n];
        LUResult res = LUDecomposition.exec(mtx);
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                y[k][i] = e[k][i];
                for (int j = 0; j < k; j++) {
                    y[k][i] -= res.L[k][j] * y[j][i];
                }
            }
            for (int k = n - 1; k >= 0; k--) {
                x[k][i] = y[k][i];
                for (int j = k + 1; j < n; j++) {
                    x[k][i] -= res.U[k][j] * x[j][i];
                }
                x[k][i] /= res.U[k][k];
            }
        }
        return x;
    }

    public static double[][] inverseByCholeskey(double[][] mtx) {
        int n = mtx.length;
        double[][] e = new double[n][n];
        for (int i = 0; i < n; i++) {
            e[i][i] = 1;
        }
        double[][] y = new double[n][n];
        double[][] x = new double[n][n];
        LUResult res = new LUResult(n);
        res.L = CholeskeyDecomposition.exec(mtx);
        res.U = Matrix.newMatrix(res.L).transposition().toArray();
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                y[k][i] = e[k][i];
                for (int j = 0; j < k; j++) {
                    y[k][i] -= res.L[k][j] * y[j][i];
                }
            }
            for (int k = n - 1; k >= 0; k--) {
                x[k][i] = y[k][i];
                for (int j = k + 1; j < n; j++) {
                    x[k][i] -= res.U[k][j] * x[j][i];
                }
                x[k][i] /= res.U[k][k];
            }
        }
        return x;
    }

    public static double zansa(double[][] a, double[] b, double[] x) {
        return v(b).minus(m(a).multiply(v(x))).norm(NormType.TWO);
    }

    public static double soutaizansa(double[][] a, double[] b, double[] x) {
        return zansa(a,b,x)/v(b).norm(NormType.TWO);
    }

    public static double sum(Function<Integer, Double> f, int start, int end) {
        double sum = 0;
        for (int j = start; j < end; j++) {
            sum += f.apply(j);
        }
        return sum;
    }

}