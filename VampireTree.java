import java.util.*;

public class VampireTree
{
    public int maxDistance(int[] num)
    {
        int n = num.length;
        int sum = 0;
        for (int x : num) sum += x;
        if (2 * (n - 1) != sum) return -1;
        int nleaves = 0;
        for (int x : num) if (x == 1) nleaves++;
        return 2 + n - nleaves - 1;
    }

    // BEGIN CUT HERE
    public static void main(String[] args)
    {
        try {
            eq(0, (new VampireTree()).maxDistance(new int[] { 1, 2, 1 }), 2);
            eq(1, (new VampireTree()).maxDistance(new int[] { 2, 2, 2 }), -1);
            eq(2, (new VampireTree()).maxDistance(new int[] { 1, 1, 1, 1, 4 }), 2);
            eq(3, (new VampireTree()).maxDistance(
                    new int[] { 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 }), -1);
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, int a, int b)
    {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected " + b + ", received " + a + ".");
    }
}
