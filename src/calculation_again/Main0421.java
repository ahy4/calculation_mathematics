package calculation_again;

/**
 * Created by noko on 2016/04/21.
 */
public class Main0421
{
    public static void main(String[] args)
    {
        System.out.println(tenToN(270.375, 8)); // == "100001110.011"
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
}
