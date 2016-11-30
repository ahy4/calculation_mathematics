package SoftwareScience2;

/**
 * Created by noko on 2016/10/24.
 */
public class ExplicitMethod
{
    public static void main(String[] args)
    {
        double dx = 0.001;
        double dy = 0.001;
        double dt = 0.001;
        double conductivity = 0.0117;
        double lambda = conductivity * dt / (dx * dx);
        if (!(lambda <= 1 / 2))
        {
            System.out.println("収束しない");
            return;
        }
        int xMax = 101; // よこ
        int yMax = 101; // たて
        int tMax = 50000;
        double[][] dataMap = new double[xMax][yMax];
        double[][][] mapLog = new double[tMax][][];

        // initialize
        for (int i = 0; i < xMax; i++)
        {
            
        }
    }
}
