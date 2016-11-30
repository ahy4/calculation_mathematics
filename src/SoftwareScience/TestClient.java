package SoftwareScience;

import java.util.Arrays;

/**
 * Created by noko on 2016/07/10.
 */
public class TestClient
{
    public static void main(String[] args)
    {
        try
        {
            Score[] scores = ParseData.parseData(FileManager.readFileSync("/Users/noko/Desktop/Seiseki.dat"));
            System.out.println(new ScoreAssessment(scores));
        } catch (Exception e)
        {
            System.out.println("ファイル読み込みエラー");
            e.printStackTrace();
        }
    }

    public static int timeMeasurement(Fn fn)
    {
        long start = System.currentTimeMillis();
        fn.apply();
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }

    static int[] randomArray(int size)
    {
        int[] result = new int[size];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = (int) (Math.random() * size);
        }
        return result;
    }

    private interface Fn
    {
        void apply();
    }
}

