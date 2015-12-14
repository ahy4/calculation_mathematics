package CalculationForTest2;

import CalcUtil.Matrix;
import CalcUtil.Vector;

import static CalcUtil.Useful.*;

/**
 * Created by noko on 2015/11/02.
 */
public class GaussElimination extends NumericalSolution{

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Matrix mtx = m(makeRandomMatrix(500));
            Vector vtr = v(makeRandomVector(500));

            Vector xs1 = v(gaussElimination(mtx.toArray(), vtr.toArray()));

            Vector xs2 = v(gaussEliminationSelectingPivot(mtx.toArray(), vtr.toArray()));
            System.out.println(
                Math.abs(vtr.minus(mtx.multiply(xs1)).norm2()) + "," +
                Math.abs(vtr.minus(mtx.multiply(xs2)).norm2())
            );
        }
    }

    public static double[] gaussElimination(double[][] matrix, double[] vector) {
        double[][] mtx = copyMatrix(matrix);
        double[] vtr = copyVector(vector);
        int n = mtx.length;
        double rate = 0;

        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                rate = mtx[i][k] / mtx[k][k];
                for (int j = k + 1; j < n; j++) {
                    mtx[i][j] -= rate * mtx[k][j];
                }
                vtr[i] -= rate * vtr[k];
            }
        }

        for (int k = n - 1; k >= 0; k--) {
            for (int j = k + 1; j < n; j++) {
                vtr[k] -= mtx[k][j] * vtr[j];
            }
            vtr[k] /= mtx[k][k];
        }
        return vtr;
    }

    public static double[] gaussEliminationSelectingPivot(double[][] mtx, double[] vec) {
        int N = mtx.length;
        int i, j, k, p;
        double[][] a = copyMatrix(mtx);
        double[] b = copyVector(vec);


        double[] w = new double[N];
        double pmax, s;

/* 前進消去（ピボット選択）*/

        for(k = 0; k < N-1; k++){  /* 第ｋステップ */
            p = k;
            pmax = Math.abs(a[k][k]);
            for(i = k+1; i < N; i++){  /* ピボット選択 */
                if(Math.abs(a[i][k]) > pmax){
                    p = i;
                    pmax = Math.abs(a[i][k]);
                }
            }

 /* エラー処理：ピボットがあまりに小さい時はメッセージを表示して終了　*/
            if(Math.abs(pmax) < 1.0e-12){
                System.out.printf("too small pivot! \n");
                return null;
            }
            if(p != k){  /* 第ｋ行と第ｐ行の交換　*/
                for(i = k; i < N; i++){
            /* 係数行列　*/
                    s = a[k][i];
                    a[k][i] = a[p][i];
                    a[p][i] = s;
                }
         /* 既知ベクトル */
                s = b[k];
                b[k] = b[p];
                b[p] = s;
            }
/* 前進消去 */
            for(i = k +1; i < N; i++){ /* 第ｉ行 */
                w[i] = a[i][k] / a[k][k];
                a[i][k] = 0.0;
         /* 第ｋ行を-a[i][k]/a[k][k]倍して、第ｉ行に加える */
                for(j = k + 1; j < N; j++){
                    a[i][j] = a[i][j] - a[k][j] * w[i];
                }
                b[i] = b[i] - b[k] * w[i];
            }
        }
/* 後退代入 */
        for(i = N - 1; i >= 0; i--){
            for(j = i + 1; j < N; j++){
                b[i] = b[i] - a[i][j] * b[j];
                a[i][j] = 0.0;
            }
            b[i] = b[i] / a[i][i];
            a[i][i] = 1.0;
        }
/* 解の表示 */
//        for(i = 0; i < N; i++){
//            System.out.printf("x["+(i+1)+"] = "+b[i]+"\n");
//        }

        return b;
    }
}
