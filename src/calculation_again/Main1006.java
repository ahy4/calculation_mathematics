package calculation_again;

import CalcUtil.Calc;
import CalcUtil.NormType;
import CalculationForTest2.GaussElimination;

import java.util.Arrays;
//import CalcUtil.*;

import static CalcUtil.Useful.*;

/**
 * Created by noko on 2016/10/06.
 */
public class Main1006
{
    public static void main(String[] args)
    {
        double[][] mtx = {
            { 2, 0, 4 },
            { -4, 3, 0 },
            { -2, 6, -5 }
        };
        double[] vtr = { 6, -1, -1 };
        double[][] mtxClone = mtx.clone();
        double[] vtrClone = vtr.clone();

//        System.out.println(Arrays.toString(GaussElimination.gaussElimination(mtx, vtr)));

        int n = mtx.length;
        double rate = 0;

        for (int k = 0; k < n - 1; k++)
        {
            for (int i = k + 1; i < n; i++)
            {
                rate = mtx[i][k] / mtx[k][k];
                for (int j = k + 1; j < n; j++)
                {
                    mtx[i][j] -= rate * mtx[k][j];
                }
                vtr[i] -= rate * vtr[k];
            }
        }

        double[] result = new double[n];
        for (int k = n-1; k >= 0; k--)
        {
            double[] left = Arrays.copyOfRange(mtx[k], k+1, n);
            double[] right = Arrays.copyOfRange(result, k+1, n);
            double sum = v(left).dot(v(right));
            result[k] = (1 / mtx[k][k]) * (vtr[k] - sum);
        }
        System.out.println(Arrays.toString(result));
        System.out.println(Calc.zansa(mtxClone,vtrClone,result)); //残差
    }
}
