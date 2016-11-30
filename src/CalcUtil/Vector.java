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

    public Vector plus(Vector vec) {
        return newVector(Calc.addVec(ary, vec.toArray()));
    }
    public Vector minus(Vector vec) {
        return plus(vec.negative());
    }
    public Vector negative() {
        return newVector(Calc.scalarMultiple(-1, ary));
    }
    public double dot(Vector vec) {
        return Calc.innProd(ary, vec.toArray());
    }
    public double multiply(Vector vec) {
        return dot(vec);
    }
    public Vector multiply(double c) {
        return newVector(Calc.scalarMultiple(c, ary));
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
    public double norm(NormType type) {
        switch (type) {
            case ONE: return norm1();
            case TWO: return norm2();
            case INF: return normInf();
            default: return 0;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        return Arrays.equals(ary, vector.ary);

    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public Matrix transpositionMatrix() {
        return Useful.m(this);
    }


    public void set(Vector v) {
        ary = v.toArray();
    }
    public void set(int idx, double value) {
        ary[idx] = value;
    }
    public double get(int idx) {
        return ary[idx];
    }
    public double length() {
        return ary.length;
    }

}
