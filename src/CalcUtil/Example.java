package CalcUtil;
import static CalcUtil.Useful.*;

/**
 * Created by yuya on 2015/06/13.
 */
public class Example {
    public static void main(String[] args) {
        double[] as = new double[100];
        for (int i = 0; i < as.length; i++)
        {
            as[i] = Math.sqrt(i+1);
        }
        System.out.println(v(as).norm1());
    }

    static double a(int i, int j)
    {
        return Math.sqrt(2*i+j);
    }

    public static void testClient() {
        // import static CalcUtil.Useful.*;
        // ↑これやると楽です

        Vector a = v(1,2,3); // 横ベクトル (1, 2, 3) の生成。可変長なのでいくつ引数を渡しても大丈夫
        double[] ns = { 3,5,6 };
        Vector b = v(ns); // 配列を渡してもいい
        Vector c = v(new double[] { 4,4,4 });
        Matrix m0 = m(a,b); // 行列の生成。基本は引数にVector型のインスタンスを複数入れることで生成



        // ↑↑ ここに、「c.」 まで書いて、ide任せにどんな補完がでるか見てください


        // 行列とベクトルの演算しても問題なし。
        // 演算可能な行列のサイズじゃない場合はArrayIndexOutOfBoundsExceptionを投げる
        System.out.println(
            m0.multiply(
                m(b, a).transposition()
            ).multiply(
                v(5,6)
            )
        );

        // 値が一致してれば別インスタンスでもtrueを返す
        System.out.println(
            m(a, b).equals(
                m(a, b).transposition().transposition()
            )
        );
    }

}
