package CalculationAgainForTest;

/**
 * Created by noko on 2016/07/06.
 */
public class BaseConversion
{

    public static String convert(String nBase, int fromBase, int toBase) {
        return tenToN(nToTen(nBase, fromBase), toBase);
    }

    public static String tenToN(double tenBase, int base)
    {
        int num = (int) tenBase;
        double den = tenBase % 1;
        return tenToNNum(num, base) + "." + tenToNDen(den, base);
    }

    private static String tenToNNum(int n, int base)
    {
        String baseNNum = "";
        for (int i = 0; n != 0 ; i++)
        {
            baseNNum = (n % base) + baseNNum;
            n = n / base;
        }
        return baseNNum;
    }

    private static String tenToNDen(double d, int base)
    {
        String baseTwoDen = "";
        for (int i = 0; d != 0; i++)
        {
            baseTwoDen += (int)(base * d);
            d = (base * d) % 1;
        }
        return baseTwoDen;
    }


    public static double nToTen(String nBase, int base)
    {
        String[] _ref = nBase.split("[.]");
        String nBaseNum = _ref[0];
        String nBaseDen;
        try { nBaseDen = _ref[1]; } catch (Exception err) { nBaseDen = "0"; }
        return nToTenNum(nBaseNum, base) + nToTenDen(nBaseDen, base);
    }

    private static double nToTenNum(String nBase, int base)
    {
        return horner(nBase, base);
    }

    private static double nToTenDen(String nBase, int base)
    {
        String reversedVal = new StringBuffer("0"+nBase).reverse().toString(); // "011"->"1100" horner法に適した形
        return horner(reversedVal, 1./base);
    }

    /**
     * ホーナー法による a[0]*x^n + a[1]*x^(n-1) + ... + a[n-2]*x^2 + a[n-1]*x^1 + a[n]*x^0 の計算
     * @param a {double[]} 係数の配列
     * @param x {double}
     * @return  {double} a[0]*x^n + a[1]*x^(n-1) + ... + a[n-2]*x^2 + a[n-1]*x^1 + a[n]*x^0
     */
    public static double horner(double[] a, double x)
    {
        double y = 0;
        for (int i = 0; i < a.length; i++)
        {
            y = y * x + a[i];
        }
        return y;
    }
    public static double horner(String a, double x)
    {
        return horner(stringToDoubleArray(a), x);
    }

    public static double[] stringToDoubleArray(String s)
    {
        char[] chars = s.toCharArray();
        double[] result = new double[chars.length];
        for (int i = 0; i < chars.length; i++)
        {
            result[i] = Integer.parseInt(Character.toString(chars[i]));
        }
        return result;
    }

}
