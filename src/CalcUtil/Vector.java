package CalcUtil;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * Created by yuya on 2015/06/13.
 */
public class Vector {
    private double[] ary;

    public Vector(double[] ary) {
        this.ary = ary;
    }

    public static Vector newVector(double[] ary) {
        return new Vector(ary);
    }
    public double[] toArray() {
        return this.ary;
    }

    public Vector multiply(double c) {
        return newVector(Calc.scalarMultiple(c, ary));
    }

    public Vector plus(Vector vec) {
        return plus(vec.toArray());
    }
    public Vector plus(double[] ary0) {
        return newVector(Calc.addVec(ary, ary0));
    }

    public Vector minus(Vector vec) {
        return minus(vec.toArray());
    }
    public Vector minus(double[] ary0) {
        return plus(Calc.scalarMultiple(-1, ary0));
    }

    public double dot(double[] ary0) {
        return Calc.innProd(ary, ary0);
    }
    public double dot(Vector vec) {
        return dot(vec.toArray());
    }
    public double multiply(Vector vec) {
        return dot(vec);
    }

    public double norm1() {
        return Calc.vecNorm1(ary);
    }
    public double norm2() {
        return Calc.vecNorm2(ary);
    }
    public double normInf() {
        return Calc.vecNormInf(ary);
    }

    public String toString() {
        return Arrays.stream(ary)
            .mapToObj((v) -> v+" ")
            .reduce("", (a, b) -> a + b)
            + "\n";
    }

    public boolean equals(Vector vec) {
        return Arrays.equals(vec.toArray(), ary);
    }

    public Vector map(BiFunction<Double, Integer, Double> callback) {
        return newVector(
            IntStream.range(0, ary.length)
                .mapToDouble((i) -> callback.apply(ary[i], i))
                .toArray()
        );
    }
    public int hashCode() {
        return this.toString().hashCode();
    }

    public Matrix transpositionMatrix() {
        return Useful.m(this);
    }
}