package SoftwareScience;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by noko on 2016/07/04.
 */



public class MergeSort
{

    private static final int INSERTIONSORT_THRESHOLD = 7;


    public static <T extends Comparable> T[] mergeSort(T[] ary)
    {
        return mergeSort(ary, (a, b) -> a.compareTo(b));
    }

    public static <T> T[] mergeSort(T[] a, Comparator<? super T> c)
    {
        T[] src = a.clone();
        T[] result = a.clone();
        mergeSort(src, result, 0, a.length, 0, c);
        return result;
    }

    /**
     * Src is the source array that starts at index 0
     * Dest is the (possibly larger) array destination with a possible offset
     * low is the index in dest to start sorting
     * high is the end index in dest to end sorting
     * off is the offset to generate corresponding low, high in src
     */
    private static void mergeSort(Object[] src,
                                  Object[] dest,
                                  int low, int high, int off,
                                  Comparator c)
    {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD)
        {
            for (int i = low; i < high; i++)
                for (int j = i; j > low && c.compare(dest[j - 1], dest[j]) > 0; j--)
                    swap(dest, j, j - 1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow = low;
        int destHigh = high;
        low += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off, c);
        mergeSort(dest, src, mid, high, -off, c);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid - 1], src[mid]) <= 0)
        {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for (int i = destLow, p = low, q = mid; i < destHigh; i++)
        {
            if (q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(Object[] x, int a, int b)
    {
        Object _ref = x[a];
        x[a] = x[b];
        x[b] = _ref;
    }

}