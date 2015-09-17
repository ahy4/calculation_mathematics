package CalculationForTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2015/07/10.
 */
public class Radix {

    public static void main(String[] args) {
        System.out.println(
                Radix.radixConvert("100.65625", 10, 4)
        );
    }

    // pre進数のnumberをaft進数に変換します。
    // 例：Radix.radixConvert("-b.c",16,8)　-> "-13.6"
    public static String radixConvert(String number, int pre, int aft) {
        return radixConvert2String(radixConvert2Double(number, pre), aft);
    }

    private double number;

    public Radix(String str, int radix) {
        number = radixConvert2Double(str, radix);
    }

    public String toString(int radix) {
        return radixConvert2String(number, radix);
    }

    private static char[] NumberChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static Map<Character, Integer> NumberMap = initNumberMap();

    private static Map<Character, Integer> initNumberMap() {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < NumberChar.length; i++) {
            map.put(NumberChar[i], i);
        }
        return map;
    }

    //a[0] + a[1]*x^1 + a[2]*x^2 + ... + a[n]*x^n
    public static double horner(double x, double[] a) {
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];
        double f = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--)
            f = f * x + a[i];
        return f;
    }

    public static double horner(double x, int[] a) {
        if (a.length == 0) return 0;
        if (a.length == 1) return a[0];
        double f = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--)
            f = f * x + a[i];
        return f;
    }

    public static int char2Integer(char c) {
        return NumberMap.get(c);
    }

    public static char integer2Char(int i) {
        return NumberChar[i];
    }

    public static double radixConvert2Double(String str, int radix) {
        int startInt = 0;
        int endInt = str.length();
        int s = 1;
        if (str.charAt(0) == '-') {
            startInt = 1;
            s = -1;
        }
        for (int i = startInt; i < str.length(); i++) {
            if (str.charAt(i) == '.') endInt = i;
        }
        String intString = str.substring(startInt, endInt);
        String smallString = "0";
        if (endInt != str.length()) smallString += str.substring(endInt + 1, str.length());
        int[] intArray = new int[intString.length()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[intArray.length - 1 - i] = char2Integer(intString.charAt(i));
        }
        int[] smallArray = new int[smallString.length()];
        for (int i = 0; i < smallArray.length; i++) {
            smallArray[i] = char2Integer(smallString.charAt(i));
        }
        double x = 0;
        x += horner(radix, intArray);
        x += horner(1.0 / (double) radix, smallArray);
        return s * x;
    }

    public static String radixConvert2String(double number, int radix) {
        int x = Math.abs((int) number);
        double y = Math.abs(number) - x;
        String str = "";
        if (number < 0) {
            str = "-";
        }
        List<Integer> intArray = new ArrayList<>();
        while (x > 0) {
            intArray.add(x % radix);
            x = x / radix;
        }

        for (int i = intArray.size() - 1; i >= 0; i--) {
            str += integer2Char(intArray.get(i));
        }
        if (y != 0) {
            if (intArray.size() == 0) str += "0";
            str += ".";
            List<Integer> smallArray = new ArrayList<>();
            int count = 0;
            while (y > 0) {
                if (20 == count++) break;
                smallArray.add((int) (y * radix));
                y = (double) radix * y - smallArray.get(smallArray.size() - 1);
            }
            for (int i = 0; i < smallArray.size(); i++) {
                str += integer2Char(smallArray.get(i));
            }
        }
        return str;
    }

}
