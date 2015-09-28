package CalcUtil;

/**
 * Created by noko on 2015/09/28.
 */
public class Test {
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

    }

}

//        for(int i=-1; i>=0; i--){
//            double s = mtx[i][n];
//            for(int j=i+1; j<n; j++){
//                s -= mtx[i][j] * mtx[j][n];
//            }
//            mtx[i][n] = s / mtx[i][i];
//            vec[i] -= s / vec[i];
//        }

