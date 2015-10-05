package CalcUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;

/**
 * Created by yuya on 2015/06/13.
 */
public class Matrix {

    private double[][] mtx;

    public Matrix(double[][] mtx) {
        this.mtx = mtx;
    }

    public static Matrix newMatrix(double[][] mtx0) {
        return new Matrix(mtx0);
    }

    public double[][] toArray() {
        return this.mtx;
    }

    public String toString() {
        return Arrays.stream(mtx)
            .map((ary) -> Vector.newVector(ary).toString())
            .reduce("", (a, b) -> a + b);
    }

    public boolean equals(Matrix mtx) {
        return Arrays.deepEquals(mtx.toArray(), this.mtx);
    }

    public Matrix plus(Matrix mtx0) {
        return newMatrix(Calc.addMat(mtx, mtx0.toArray()));
    }
    public Matrix minus(Matrix mtx0) {
        return this.plus(mtx0.negative());
    }
    public Matrix negative() {
        return newMatrix(mtx).multiply(-1);
    }
    public Matrix multiply(Matrix mtx0) {
        return newMatrix(Calc.multipleMat(mtx, mtx0.toArray()));
    }
    public Vector multiply(Vector vec) {
        return Vector.newVector(Calc.matVec(mtx, vec.toArray()));
    }
    public Matrix multiply(double num) {
        return newMatrix(Calc.scalarMultiple(num, mtx));
    }

    public double norm1() {
        return Calc.matNorm1(mtx);
    }
    public double normInf() {
        return Calc.matNormInf(mtx);
    }

    public Matrix transposition() {
        return newMatrix(Calc.matTransposition(mtx));
    }

    public Matrix map(TriFunction<Double, Integer, Integer, Double> callback) {
        return newMatrix(Calc.matrixMap(mtx, (x, y) -> {
            return callback.apply(mtx[x][y], x, y);
        }));
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    // TODO: .inverse()と、固有値求めるメソッドがほしい
    public void set(Matrix m) {
        mtx = m.toArray();
    }
    public void set(int i, int j, double v) {
        mtx[i][j] = v;
    }
    public double get(int i, int j) {
        return mtx[i][j];
    }
    public int rows() {
        return mtx.length;
    }
    public int columns() {
        return mtx[0].length;
    }

}

interface TriFunction <T, U, V, W> {
    W apply(T v, U x, V y);
}