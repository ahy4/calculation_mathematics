package Test;

import CalculationAgainForTest.*;

/**
 * Created by noko on 2016/07/07.
 */
public class Test05
{
    public static void main(String[] args)
    {
//        System.out.println(
//            Double.parseDouble(BaseConversion.convert("742.12", 8, 10)) + 2.125 // 484.28125
//        );
//        System.out.println(BaseConversion.convert("484.28125", 10, 2));

//        for (int i = 0; true; i++) if (S(i+1) == S(i))
//        {
//            System.out.println(i);
//            break;
//        }

//        System.out.println(13  / Math.log10(2));

        SolveAlgorithm.newton();


    }

    static double a(int n)
    {
        return n == 1 ? 1 : a(n-1) / (n*n);
    }

    static double S(int n)
    {
        double sum = 0;
        for (int i = n; i >= 1; i--)
        {
            sum += a(i);
        }
        return sum;
    }
}
