package SoftwareScience;

import java.util.Arrays;

/**
 * Created by noko on 2016/07/09.
 */
public class ParseData
{
    public static Score[] parseData(String data)
    {
        String[] lines = data.split("\\n");
        Score[] scores = new Score[lines.length];
        int count = 0;
        for (int i = 0; i < lines.length; i++) if (!lines[i].equals(""))
        {
            int[] _ref = stringsToInts(lines[i].split("\\s+"));
            int[] ary = Arrays.copyOfRange(_ref, 1, _ref.length);
            scores[i] = new Score(ary);
            count++;
        }
        return Arrays.copyOf(scores, count); // 空データ分消す
    }

    private static int[] stringsToInts(String[] xs)
    {
        int[] result = new int[xs.length];
        for (int i = 0; i < xs.length; i++)
        {
            try {
                result[i] = Integer.parseInt(xs[i]);
            } catch (Exception e) {
                result[i] = 0;
            }
        }
        return result;
    }
}
