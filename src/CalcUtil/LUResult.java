package CalcUtil;

/**
 * Created by noko on 2015/12/14.
 */
public class LUResult {
    LUResult(int n) {
        L = new double[n][n];
        U = new double[n][n];
    }

    public double[][] L;
    public double[][] U;
}
