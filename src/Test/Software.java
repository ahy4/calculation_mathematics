package Test;

/**
 * Created by noko on 2016/05/09.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
// Source File Name:   Software.java

import java.io.*;
    import java.util.*;

public class Software
{

    public Software()
    {
    }

    public static int inputMatrix(String s, double ad[][])
    {
        String s1 = "";
        int j = 1;
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
            String s3;
            while((s3 = bufferedreader.readLine()) != null)
            {
                StringTokenizer stringtokenizer = new StringTokenizer(s3, " ");
                int i = stringtokenizer.countTokens();
                for(int k = 1; k <= i; k++)
                    try
                    {
                        String s2 = stringtokenizer.nextToken();
                        ad[j][k] = Double.valueOf(s2).doubleValue();
                    }
                    catch(NoSuchElementException nosuchelementexception)
                    {
                        System.out.println((new StringBuilder()).append("No Token").append(s3).toString());
                    }

                j++;
            }
            bufferedreader.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            System.out.println((new StringBuilder()).append("File Not Found ---> ").append(s).toString());
        }
        catch(IOException ioexception)
        {
            System.out.println(ioexception.getMessage());
        }
        return j - 1;
    }

    public static int inputVector(String s, double ad[])
    {
        String s1 = "";
        int j = 1;
        try
        {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
            String s3;
            while((s3 = bufferedreader.readLine()) != null)
            {
                StringTokenizer stringtokenizer = new StringTokenizer(s3, " ");
                int i = stringtokenizer.countTokens();
                int k = 1;
                while(k <= i)
                {
                    try
                    {
                        String s2 = stringtokenizer.nextToken();
                        ad[j] = Double.valueOf(s2).doubleValue();
                        j++;
                    }
                    catch(NoSuchElementException nosuchelementexception)
                    {
                        System.out.println((new StringBuilder()).append("No Token").append(s3).toString());
                    }
                    k++;
                }
            }
            bufferedreader.close();
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            System.out.println((new StringBuilder()).append("File Not Found ---> ").append(s).toString());
        }
        catch(IOException ioexception)
        {
            System.out.println(ioexception.getMessage());
        }
        return j - 1;
    }

    static void matrixDisplay(int i, double ad[][], String s)
    {
        for(int j = 1; j <= i; j++)
        {
            for(int k = 1; k <= i; k++)
                System.out.printf((new StringBuilder()).append("%").append(s).append("f").toString(), new Object[] {
                    Double.valueOf(ad[j][k])
                });

            System.out.println("");
        }

    }

    static void vectorDisplay(int i, double ad[], String s)
    {
        for(int j = 1; j <= i; j++)
            System.out.printf((new StringBuilder()).append("%").append(s).append("f").toString(), new Object[] {
                Double.valueOf(ad[j])
            });

        System.out.println("");
    }

    public static String inputKeybordString(String s)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        String s1 = scanner.next();
        return s1;
    }

    public static int inputKeybordInteger(String s)
    {
        String as[] = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        String s1 = scanner.next();
        int i = s1.length();
        for(int j = 0; j < i; j++)
        {

            boolean flag = false;
            int l = 0;
            do
            {
                if(l >= 10)
                    break;
                if(s1.substring(j, j + 1).equals(as[l]))
                {
                    flag = true;
                    break;
                }
                l++;
            } while(true);
            if(!flag)
            {
                System.out.println((new StringBuilder()).append("  ").append(s1).append(" is not integer value").toString());
                System.exit(1);
            }
        }

        int k = Integer.valueOf(s1).intValue();
        return k;
    }
}
