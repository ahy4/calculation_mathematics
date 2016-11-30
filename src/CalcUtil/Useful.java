package CalcUtil;

/**
 * Created by yuya on 2015/06/13.
 */
public class Useful {

    public static Vector v(double... args) {
        return Vector.newVector(args);
    }
    public static Vector v(double v) {
        return Vector.newVector(new double[]{v});
    }

    public static Matrix m(double[]... mtx) {
        return Matrix.newMatrix(mtx);
    }

    public static Matrix m(double... xs) {
        return m(v(xs));
    }

    public static Matrix m(Vector... vectors) {
        double[][] vs = new double[vectors.length][];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = vectors[i].toArray();
        }
        return Matrix.newMatrix(vs);
    }
}
