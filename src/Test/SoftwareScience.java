package Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by noko on 2016/04/25.
 */
public class SoftwareScience {

    public static void main(String[] args) {
        testClient();
        Math.random();
    }

    public static void testClient() {

        double[][] mtx = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 90.01},
            {10, 1111111, 12}
        };

        // 配列を、ユーザーホーム下のデスクトップ上に画像として保存します
        outputMatrix(mtx);

        // 第二引数で保存先を指定することも可能です
//      outputMatrix(mtx, "/Users/noko/Desktop/result.gif");

    }

    /**
     * 行列を画像化して保存します。インターネット接続がオフラインだと動きません
     * @param mtx 画像化したい行列
     * @param fileTarget 保存先のパスです。
     */
    public static void outputMatrix(double[][] mtx, String fileTarget) {
        String tex = parseTex(mtx);
        System.out.println(tex);
        String url = texUrl(tex);
        saveImg(url, fileTarget);
    }

    /**
     * 行列を画像化して保存します。保存先は、macの場合はデスクトップ上になってます。インターネット接続がオフラインだと動きません
     * @param mtx 画像化したい行列
     * @see SoftwareScience::outputMatrix(mtx, fileTarget)
     */
    public static void outputMatrix(double[][] mtx) {
        outputMatrix(mtx, System.getProperty("user.home") + "/Desktop/result.gif");
    }

    private static String parseTex(double[][] mtx) {
        String tex = "";
        tex += "\\left(\\begin{array}{ccc}";
        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[j].length; j++) {
                tex += parseNumberForTex(mtx[i][j]) + " & ";
            }
            tex = tex.substring(0, tex.length() - 3);
            tex += " \\\\";
        }
        tex += " \\end{array}\\right)";
        return tex;
//        \left(
//            \begin{array}{ccc}
//        a & b & c \\
//        d & e & f \\
//        g & h & i
//        \end{array}
//        \right)
    }

    private static String texUrl(String tex) {
        return "https://latex.codecogs.com/gif.latex?%5Cdpi%7B300%7D%20%5Cbg_white%20" + encodeUrl(tex);
//        return "https://latex.codecogs.com/gif.latex?%5Cleft%28%20%5Cbegin%7Barray%7D%7Bccc%7D%20a%20%26%20b%20%26c%5C%5C%20d%20%26%20e%20%26f%5C%5C%20g%20%26%20h%20%26i%5C%5C%20%5Cend%7Barray%7D%20%5Cright%29";
    }

    private static void saveImg(String inputUrl, String fileTarget) {
        try {
            URL url = new URL(inputUrl); // ダウンロードする URL
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();

            File file = new File(fileTarget); // 保存先
            FileOutputStream out = new FileOutputStream(file, false);
            int b;
            while((b = in.read()) != -1){
                out.write(b);
            }

            out.close();
            in.close();
            System.out.println("save success");

        } catch(Exception e) {
            System.out.println("save failed");
        }
    }

    private static String encodeUrl(String url) {
        try {
            String encodeStr = URLEncoder.encode(url, "UTF-8");
            encodeStr = encodeStr.replace("+", "%20");
            return encodeStr;

        } catch (Exception e) {
            return "";
        }
    }

    private static boolean hasDenominator(double n) {
        return n % 1 > 0.00000001;
    }

    private static String parseNumberForTex(double n) {
        if (hasDenominator(n)) {
            return String.valueOf(n);
        }
        return String.valueOf((int)n);
    }
}
