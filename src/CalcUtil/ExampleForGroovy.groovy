package CalcUtil
import static CalcUtil.Useful.*
// import static CalcUtil.Useful.*;
// ↑これやると楽です

Vector a = v(1,2,3) // 横ベクトル (1, 2, 3) の生成。可変長なのでいくつ引数を渡しても大丈夫
double[] ns = [3,5,6];
Vector b = v(ns) // 配列を渡してもいい
Matrix m0 = m(a,b) // 行列の生成。基本は引数にVector型のインスタンスを複数入れることで生成

Vector c = v((Double[])[1,2,3,4]) // さすがに型が分かる必要がある。javaで直接 v({1,2,3}) って書けないのと理由は同じ



// ↑↑ ここに、「c.」 まで書いて、ide任せにどんな補完がでるか見てください


// 行列とベクトルの演算しても問題なし。
// 演算子オーバーロードは気づいたものはしてる
// 演算可能な行列のサイズじゃない場合はArrayIndexOutOfBoundsExceptionを投げる
// Vector型とMatrix型のtoStringはそれっぽくオーバーロードしてある
println(
    m0 * (m(b,a).transposition()) * v(5,6)
)

// 値が一致してれば同一インスタンスでなくてもtrueを返す
println(
    m(a, b) == m(a, b).transposition().transposition()
)
