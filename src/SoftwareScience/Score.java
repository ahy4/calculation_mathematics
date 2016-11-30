package SoftwareScience;

import java.util.HashMap;

/**
 * Created by noko on 2016/07/09.
 */
public class Score
{
    private int id;
    private int math2B;
    private int math3B;
    private int english;
    private int physicsB;
    private int chemistryB;
    private int biologyB;
    private int geoscienceB;

    private HashMap<String, ScienceScore> selectedScience;
    private Integer sum = null;

    public Score(int[] ary)
    {
        bulkSet(ary);
        selectedScience = SelectedScoreOfScience.select(physicsB, chemistryB, biologyB, geoscienceB);
    }

    private void bulkSet(int[] ary)
    {
        this.id = ary[0];
        this.math2B = ary[1];
        this.math3B = ary[2];
        this.english = ary[3];
        this.physicsB = ary[4];
        this.chemistryB = ary[5];
        this.biologyB = ary[6];
        this.geoscienceB = ary[7];
    }

    public int getId()
    {
        return this.id;
    }

    public int getSum()
    {
        this.sum = math2B + math3B + english + selectedScience.get("first").score + selectedScience.get("second").score;
        return this.sum;
    }

    public HashMap<String, ScienceScore> getSelectedScience()
    {
        return selectedScience;
    }

    @Override
    public String toString()
    {
        return (
            "[ " +
            id + "," +
            math2B + "," +
            math3B + "," +
            english + "," +
            physicsB + "," +
            chemistryB + "," +
            biologyB + "," +
            geoscienceB +
            " ]"
        );
    }

}


