import java.util.*;

public class VampireTree
{
    int n;
    int[] t; // parent to ancestor edges
    boolean[][] u; // parent to ancestor edges
    int[] c; // count of remaining nodes
    int[] v; // reference count
    int[] d; // index of last added descendant

    int ret = -1;

    class Span
    {
        public int w = 0;
        public int h = 0;
    }

    Span f(int i)
    {
        Span s = new Span();
        for (int j = 0; j < n; ++j) {
            if (!u[i][j])
                continue;
            Span s2 = f(j);
            if (0 < s.h) {
                s.w = Math.max(s.w, s.h + s2.h + 1);
            }
            s.w = Math.max(s.w, s2.w);
            s.h = Math.max(s.h, s2.h + 1);
        }
        return s;
    }

    void calc()
    {
        boolean complete = true;
        for (int j = 0; j < n; ++j) {
            if (0 != c[j]) {
                complete = false;
                break;
            }
        }
        if (!complete)
            return;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                u[i][j] = (0 != j && i == t[j]);
        Span s = f(0);
        ret = s.w;
    }

    void rec(int i)
    {
        if (c[i] <= 0)
            return;

        --c[i];
        ++v[i];

        if (0 == c[i]) {
            if (0 == i)
                calc();
            else {
                if (-1 != t[i]) {
                    ++c[t[i]];
                    rec(t[i]);
                    --c[t[i]];
                }
            }
            ++c[i];
            return;
        }

        for (int j = d[i] + 1; j < n; ++j) {
            if (0 < v[j] || c[i] < c[j])
                continue;
            c[i] -= c[j];
            t[j] = i;
            d[i] = j;
            rec(j);
            c[i] += c[j];
            t[j] = -1;
        }

        ++c[i];
        --v[i];
        d[i] = i;
    }

    public int maxDistance(int[] num)
    {
        n = num.length;
        Arrays.sort(num);
        for (int i = 0; i < n / 2; ++i) {
            int tmp = num[i];
            num[i] = num[n - i - 1];
            num[n - i - 1] = tmp;
        }
        t = new int[n];
        for (int i = 0; i < n; ++i)
            t[i] = -1;
        u = new boolean[n][n];
        c = num;
        c[0] += 1;
        v = new int[n];
        d = new int[n];
        for (int i = 0; i < n; ++i)
            d[i] = i;
        rec(0);
        return ret;
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
