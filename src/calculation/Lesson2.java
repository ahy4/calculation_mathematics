package calculation;

import static java.lang.Math.*;

/**
 * Created by yuya on 2015/04/27.
 */

/*
��ϊ�(10�i��->�Q�i��)

(270.375)_10 -> (?)_2

���������Ə��������ɕ����čl����

<����>
(270)_10 = 2*10^2 + 7*10^1 + 0*10^0
= (100001110)_2

�m�F
1*2^8 + ... + 1*2^3 + 1*2^2 + 1*2^1 + 0*2^0 = 270

<����>
(0.375)_10 = 3*10^(-1) + 7*10^(-2) + 5*10^(-3)

0.375 * 2 = 0.750
0.75 * 2 = 1.50
0.5 * 2 = 1.0 (�I��)

��̌��ʂ�1���ڂɒ��ڂ���
(0.375) = (0.011)_2

�m�F
0*2^(-1) + 1*2^(-2) + 1*2^(-3)
= 0 + 0.25 + 0.125
= 0.375

=> 100001110.011

���@�z�����I�ɁA�L�����ŕ\���ł��Ȃ��ꍇ������


������
�E�^����ꂽ�����𐮐������Ə��������ɕ����ĕϊ����s���A���ׂĕ\��


*/

public class Lesson2
{
    public static void main(String[] args)
    {
        double input = 270.375;


        int integerPart = (int) input;
        double decimalPart = input % 1;

        System.out.println(Integer.toBinaryString((int) input));
        System.out.println(step2(integerPart) + ", " + step3(decimalPart));
    }

    public static int step2(int integerPart)
    {
        int[] saveData = new int[100];

        int integer = integerPart;
        for (int i = 0; integer != 0 && i < saveData.length; i++)
        {
            saveData[i] = integer % 2;
            integer /= 2; // �������I
        }

        int sum = 0;
        for (int i = 0; i < saveData.length; i++)
        {
            sum += saveData[i] * pow(10, i);
        }

        return sum;
    }

    public static double step3(double decimalPart)
    {
        int[] saveData = new int[100];

        double decimal = decimalPart;
        for (int i = 0; decimal != 0 && i < saveData.length; i++)
        {
            saveData[i] = (int) (2 * decimal);
            decimal = 2 * decimal - saveData[i];
        }

        double sum = 0;
        for (int i = 0; i < saveData.length; i++)
        {
            sum += saveData[i] * pow(10, -(i+1));
        }

        return sum;

    }
}
