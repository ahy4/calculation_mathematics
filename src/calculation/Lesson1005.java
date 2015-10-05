package calculation;

import CalcUtil.*;
import static CalcUtil.Useful.*;

/**
 * Created by noko on 2015/10/05.
 */
public class Lesson1005 {
    public static void main(String[] args) {
        double[][] mtx = {
            {1.0, 2.0, 1.0, 2.0, 1.0},
            {2.0, 3.0, 2.0, 3.0, 2.0},
            {1.0, 2.0, 3.0, 4.0, 5.0},
            {4.0, 3.0, 8.0, 1.0, 2.0},
            {8.0, 2.0, 4.0, 1.0, 9.0}
        };
        double[] vec = {7, 7, 7, 7, 7};
        int n = mtx.length;
        for (int k = 0; k < n - 1; k++) { // ピボットを消す回数が k
            for (int i = k + 1; i < n; i++) { // すべての行において
                double rate = mtx[i][k] / mtx[k][k];
                mtx[i][k] = 0; // (省略可)
                for (int j = k + 1; j < n; j++) { // 必要な列において引き算を実行
                    mtx[i][j] -= rate * mtx[k][j];
                }
                vec[i] -= rate * vec[k];
            }
        }

        System.out.println(
            Useful.m(mtx)
        );
        System.out.println(
            Useful.v(vec)
        );


        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                vec[i] -= mtx[i][j] * vec[j];
                mtx[i][j] = 0;
            }
            vec[i] = vec[i] / mtx[i][i];
            mtx[i][i] = 1;
        }

        System.out.println(
            m(mtx)
        );
        System.out.println(
            v(vec)
        );
    }

    public static void testClient01() {
        Matrix mtx = m(
            v(1, 2, 1, 2, 1),
            v(2, 3, 2, 3, 2),
            v(1, 2, 3, 4, 5),
            v(4, 3, 8, 1, 2),
            v(8, 2, 4, 1, 9)
        );
        Vector vec = v(7,7,7,7,7);
        int n = mtx.rows();

        for (int k = 0; k < n - 1; k++) { // ピボットを消す回数が k
            for (int i = k + 1; i < n; i++) { // すべての行において
                double rate = mtx.get(i,k) / mtx.get(k,k);
                mtx.set(i, k, 0); // (省略可)
                for (int j = k + 1; j < n; j++) { // 必要な列において引き算を実行
                    mtx.set(i, j, mtx.get(i, j) - rate * mtx.get(k, j));
                }
                vec.set(i, vec.get(i) - rate * vec.get(k));
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                vec.set(i, vec.get(i) - mtx.get(i, j) * vec.get(j));
                mtx.set(i, j, 0);
            }
            vec.set(i, vec.get(i) / mtx.get(i, i));
            mtx.set(i, i, 1);
        }
    }

}
