package SoftwareScience;

import java.util.*;

/**
 * Created by noko on 2016/07/09.
 */
public class SelectedScoreOfScience
{

    public static HashMap<String, ScienceScore> select(int physics, int chemistry, int biology, int geoscience)
    {
        HashMap<String, ScienceScore> data = new HashMap<String, ScienceScore>();

        int[] scoreAry = { physics, chemistry, biology, geoscience };
        String[] subjectAry = { "physics", "chemistry", "biology", "geoscience" };
        ScienceScore[] scores = new ScienceScore[scoreAry.length];
        for (int i = 0; i < scoreAry.length; i++)
        {
            scores[i] = new ScienceScore(subjectAry[i], scoreAry[i]);
        }
        ArrayList<HashMap<String, Integer>> sorted = new ArrayList<>();
        Arrays.sort(scores, (a, b) -> Integer.compare(a.score, b.score));

        data.put("first", scores[scores.length-1]);
        data.put("second", scores[scores.length-2]);
        return data;
    }

}

