package SoftwareScience;

import java.util.HashMap;

/**
 * Created by noko on 2016/07/09.
 */
public class ScoreAssessment
{

    Score[] scores;
    private  HashMap<String, String> languageMapping;


    public ScoreAssessment(Score[] scores)
    {
        this.createLanguageMapping();
        this.scores = scores;
        this.sortByScore();
    }

    public void sortByScore()
    {
        this.scores = MergeSort.mergeSort(this.scores, (a, b) -> {
            return Integer.compare(b.getSum(), a.getSum());
        });
    }

    // 順位, id, 点数, 使用科目
    @Override
    public String toString()
    {
        String result = "";
        int rank = 1;
        int prevScore = 0;
        for (int i = 0; i < scores.length; i++)
        {
            Score score = scores[i];
            HashMap<String, ScienceScore> selectedScience = score.getSelectedScience();
            String science1 = subjectToJapanese(selectedScience.get("first").subject);
            String science2 = subjectToJapanese(selectedScience.get("second").subject);

            if (prevScore != score.getSum())
            {
                rank = i+1;
                if (rank > 300) break;
            }
            result += rank + "," + score.getId() + "," + score.getSum() + "," + science1 + "," + science2 + "\n";
            prevScore = score.getSum();
        }
        return result;
    }

    public String subjectToJapanese(String english)
    {
        return this.languageMapping.get(english);
    }

    private void createLanguageMapping()
    {
        String[] subjectEnglish = { "physics", "chemistry", "biology", "geoscience" };
        String[] subjectJapanese = { "物理B", "化学B", "生物学B", "地学B" };
        this.languageMapping = new HashMap<>();
        for (int i = 0; i < subjectEnglish.length; i++)
        {
            languageMapping.put(subjectEnglish[i], subjectJapanese[i]);
        }
    }
}
