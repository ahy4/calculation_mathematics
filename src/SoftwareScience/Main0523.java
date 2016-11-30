package SoftwareScience;

/**
 * Created by noko on 2016/05/23.
 */
public class Main0523
{
    public static void main(String[] args)
    {
        for (int n = 0; n < 30000; n+=100) {
            int[] xs = new int[n];
            for (int i = 0; i < n; i++) {
                xs[i] = (int)(Math.random() * n);
            }
            long start = System.nanoTime();
            bubbleSort(xs);
            long end = System.nanoTime();
            int result = (int) (end - start) / 1000000;
            System.out.println(result);
        }
    }

    private static int[] arrayCopy(int[] ary)
    {
        int[] clone = new int[ary.length];
        for (int i = 0; i < ary.length; i++) {
            clone[i] = ary[i];
        }
        return clone;
    }

    public static int[] bubbleSort(int[] ary)
    {
        int n = ary.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = n - 1; i < j; j--)
            {
                if (ary[j] < ary[j-1])
                {
                    int _ref = ary[j];
                    ary[j] = ary[j-1];
                    ary[j-1] = _ref;
                }
            }
        }
        return ary;
    }

    private static String intArraytoString(int[] ary)
    {
        String result = "";
        result += "[ ";
        for (int i = 0; i < ary.length - 1; i++) {
            result += ary[i] + ", ";
        }
        result += ary[ary.length-1];
        result += " ]";
        return result;
    }
}
