package CalculationForTest;

/**
 * Created by noko on 2015/07/09.
 */
public class BaseConversion {

    public static void main(String[] args) {
        // 使い方の例：
        System.out.println(
            new BaseConversion("134").fromBase(10).toBase(16)
        );
    }


    Integer fromBase = null;
    int toBase;
    Double tenBaseValue = null;
    String resultValue;
    String originValue;

    BaseConversion(double x_10) {
        tenBaseValue = x_10;
        originValue = String.valueOf(x_10);
        fromBase = 10;
    }
    BaseConversion(String x_n) {
        originValue = x_n;
    }

    public BaseConversion fromBase(int base) {
        fromBase = base;
        return this;
    }

    public String toBase(int base) {
        toBase = base;

        if (fromBase == null) {
            throw new IllegalStateException("Please tell me fromBase value. \nUse .fromBase() .");
        }
        tenBaseValue = tenBaseValue != null && fromBase == 10 ? tenBaseValue : nToTen(originValue);
        resultValue = tenToN(tenBaseValue);
        return resultValue;
    }

    public String result() {
        return resultValue;
    }


    private String tenToN(double x_10) {
        return integerProcess((int) x_10) + decimalProcess(x_10 % 1);
    }

    private double nToTen(String x_n) {
        String[] tmp = x_n.split("[.]");
        String xnInt = tmp[0], xnDecimal;
        try { xnDecimal = tmp[1]; } catch (Exception e) { xnDecimal = ""; }
        int integer = 0;
        for (int i = 0; i < xnInt.length(); i++) {
            integer = integer * fromBase + Integer.parseInt(String.valueOf(xnInt.charAt(i)), Character.MAX_RADIX);
        }
        double decimal = 0;
        for (int i = 0; i < xnDecimal.length(); i++) {
            decimal = decimal / fromBase + Integer.parseInt(String.valueOf(xnDecimal.charAt(i)), Character.MAX_RADIX);
        } decimal /= fromBase;

        return integer + decimal;
    }

    private String integerProcess(int x_10) {
        String x_n = "";
        for (int i = 0; i < 100 && x_10 != 0; i++) {
            int v = x_10 % toBase;
            x_n += v <= 9 ? v : Character.toString((char) ('a' + v - 10));
            x_10 /= toBase;
        }
        return reverse(x_n);
    }

    private String reverse(String str) {
        String reversed = "";
        for (int i = str.length()-1; i >= 0; reversed += str.charAt(i), i--);
        return reversed;
    }

    private String decimalProcess(double x_10) {
        String x_n = "";
        for (int i = 0; i < 100 && x_10 != 0; i++) {
            int v = (int) (toBase * x_10);
            x_n += v <= 9 ? v : Character.toString((char) ('a' + v - 10));
            x_10 = (toBase * x_10) % 1;
        }
        return x_n == "" ? "" : "." + x_n;
    }
}
