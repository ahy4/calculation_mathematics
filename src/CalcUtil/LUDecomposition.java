package CalcUtil;

import CalcUtil.*;
import static CalcUtil.Useful.*;

/**
 * Created by noko on 2015/11/02.
 */
public class LUDecomposition {
    public static LUResult exec(double[][] a) {
        int n = a.length;
        int t, i, j;
        for (t = 0; t < n - 1; t++) {
            for (i = t + 1; i < n; i++) {
                a[i][t] /= a[t][t];
                for (j = t + 1; j < n; j++) a[i][j] -= a[i][t] * a[t][j];
            }
        }
        LUResult res = new LUResult(n);
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i < j) {
                    res.U[i][j] = a[i][j];
                    res.L[i][j] = 0;
                } else if (i == j) {
                    res.U[i][j] = a[i][j];
                    res.L[i][j] = 1;
                } else { // i > j
                    res.U[i][j] = 0;
                    res.L[i][j] = a[i][j];
                }
            }
        }
        return res;
    }
//    public static void main(String[] args) {
//        double[][] A = {{1, 2, 1, 2, 1}, {2, 3, 2, 3, 2}, {1, 2, 3, 4, 5}, {4, 3, 8, 1, 2}, {8, 2, 4, 1, 9}};
//        double[] b = {7, 7, 7, 7, 7};
//        int n = A.length;
//        //左下L右上U  Lu分解
//        for (int k = 0; k < n - 1; k++) {
//            for (int i = k + 1; i < n; i++) {
//                double alpha = A[i][k] / A[k][k];
//                for (int j = k + 1; j < n; j++) {
//                    A[i][j] = A[i][j] - alpha * A[k][j];
//                }
//                A[i][k] = A[i][k] / A[k][k];
//            }
//        }
//        System.out.println(m(A));
//        System.out.println(v(b));
//        // 前進代入過程
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//                b[i] -= A[i][j] * b[j];
//            }
//        }
//        System.out.println(m(A));
//        System.out.println(v(b));
//        //後退代入過程
//        for (int i = n - 1; i >= 0; i--) {
//            for (int j = i + 1; j < n; j++) {
//                b[i] -= A[i][j] * b[j];
//            }
//            b[i] = b[i] / A[i][i];
//        }
//
//        Calc.printMat(A);
//        Calc.printVec(b);
//
//        /*1.0,2.0,1.0,2.0,1.0,
//        2.0,-1.0,0.0,-1.0,0.0,
//        1.0,-0.0,2.0,2.0,4.0,
//        4.0,5.0,2.0,-6.0,-10.0,
//        8.0,14.0,-2.0,-0.5,4.0,
//        */
//    }
}
