package SoftwareScience;

import java.util.Arrays;

/**
 * Created by noko on 2016/06/06.
 */
public class Main0606 {
    public static void main(String[] args) {
        int[] ary = { 5,6,9,1,3,7,3,9,9 };
        System.out.println(stringify(gnormSort(ary)));
    }
    public static int[] gnormSort(int[] ary) {
        int[] x = Arrays.copyOf(ary, ary.length);
        int n = x.length;
        int i = 1;
        int acc = 0;
        while (i < n) {
            if (x[i-1] <= x[i]) {
                if (i < acc) {
                    i = acc;
                } else {
                    i++;
                }
            } else {
                int a = x[i-1];
                x[i-1] = x[i];
                x[i] = a;
                acc = i;
                i--;
                if (i == 0) {
                    i+=2;
                }
            }
        }
        return x;
    }
    public static String stringify(int[] ary) {
        String line = "";
        for (int i = 0; i < ary.length; i++) {
            line += ary[i] + " ";
        }
        return line;
    }
}
