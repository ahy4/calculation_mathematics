package SoftwareScience2;

import java.io.*;

public class Heat_Flow_Dirichlet_Double
{
    public static void main(String[] args) throws Exception
    {
        int i, j;
        double dt = 0.001;
        double dx = 0.01;
        double kappa = 0.0117; // 金の熱伝達係数 m^2/s
        double lambda = kappa * dt / dx / dx;
        System.out.println(" alpha = " + lambda);
        if (lambda >= 0.5) return;
        int n = 101; // 格子点の数
        int m = 50000; // 時間格子の数
        double T[] = new double[n];
        double TT[] = new double[n];
        double maxTemp = 1000; // 最高温度は１/２＜500℃＞
// 初期条件の設定*********************
        for (j = 0; j < n; j++)
        {
            if (j <= n / 2)
            {
                T[j] = maxTemp * dx * j + 273.;
            }
            else
            {
                T[j] = maxTemp * (1 - dx * j) + 273.;
            }
        }
        int nn = 0;
        T[0] = 273.0; // 境界条件として左端の温度を0℃とする
        T[n - 1] = 273.0; // 境界条件として左端の温度を0℃とする
        FileWriter fw = new FileWriter("Heat_Flow_Dirichlet_Double_new.csv"); // ファイル名の指定
        for (i = 0; i < n; i++)
        {
            fw.write(" " + i + " , ");
        }
        fw.write(" \r\n ");
// 初期値の格納
        for (i = 0; i < n; i++)
        {
            fw.write(" " + (T[i] - 273) + " , ");
        }
        fw.write(" \r\n ");
        // 内部温度の計算
        while (nn <= m)
        {
            nn++;
            for (i = 1; i < (n - 1); i++)
            {
                TT[i] = T[i] + lambda * (T[i + 1] + T[i - 1] - 2 * T[i]);
            }
            for (i = 1; i < (n - 1); i++)
            {
                T[i] = TT[i];
            }
// 計算結果の印刷
            if ((nn) % 1000 == 0)
            {
                for (i = 0; i < n; i++)
                {
                    fw.write(" " + (T[i] - 273.) + " , ");
                }
                fw.write(" \r\n ");
            }
        }
        fw.close();
    }
}