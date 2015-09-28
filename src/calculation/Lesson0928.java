package calculation;

/**
 * Created by noko on 2015/09/28.
 */

import CalcUtil.*;

import javax.jws.soap.SOAPBinding;

/**
 * ガウスの消去法
 * http://gi.ics.nara-wu.ac.jp/~takasu/lecture/old/archives/H16-keisankiJikken1-1.pdf
 * http://www.cs.tsukuba.ac.jp/~takahito/algebra1/system.pdf
 */

public class Lesson0928 {

    public static void main(String[] args){
        double[][] mtx = {
            {1.0, 2.0, 1.0, 2.0, 1.0},
            {2.0, 3.0, 2.0, 3.0, 2.0},
            {1.0, 2.0, 3.0, 4.0, 5.0},
            {4.0, 3.0, 8.0, 1.0, 2.0},
            {8.0, 2.0, 4.0, 1.0, 9.0}
        };
        double[] vec = { 7,7,7,7,7 };
        int n = mtx.length;
        for (int k = 1; k <= n - 1; k++) {
            for (int i = k + 1; i <= n; i++) {
                double rate = mtx[i-1][k-1];
                for (int j = k + 1; j <= n; j++) {
                    mtx[i][j] -= rate * mtx[k][j];
                }
                vec[i] -= rate * vec[k];
            }
        }


    }





    Lesson0928(double[][] mtx) {
        a = mtx;
    }

    double[][] a;
    int N=a.length;

    //Gaussの消去法で連立方程式を解く
    void gauss(){
        //前進消去
        double s;
        for(int k=0; k<N-1; k++){
            for(int i=k+1; i<N; i++){
                s=a[i][k]/a[k][k];
                for(int j=k+1; j<=N; j++){
                    a[i][j] -= s * a[k][j];
                }
            }
        }
        //後退代入
        for(int i=N-1; i>=0; i--){
            s=a[i][N];
            for(int j=i+1; j<N; j++){
                s -= a[i][j] * a[j][N];
            }
            a[i][N] = s/a[i][i];
        }
    }
//    public static void main(String[] args) {
//        Lesson0928 gs= new Lesson0928(new double[][]{
//            {1.0,1.0,1.0,1.0,10,0},
//            {2.0,1.0,3.0,2.0,21.0},
//            {1.0,3.0,2.0,1.0,17.0},
//            {3.0,2.0,1.0,1.0,14.0}
//        });
//        gs.gauss();
//        //解の表示
//        System.out.println("ガウスの消去法による連立1次方程式の解");
//        for(int i=0; i<gs.N; i++){
//            System.out.println("x["+i+"] = "+gs.a[i][gs.N]);
//        }
//    }
}
