
public class FoxesOfTheRoundTable {
    public int[] minimalDifference(int[] h) {
        final int n = h.length;
        int[] p = new int[n];
        {
            boolean[] v = new boolean[n];
            for (int i = 0; i < n; ++i) {
                int curr = -1;
                for (int j = 0; j < n; ++j) {
                    if (v[j])
                        continue;
                    if (curr < 0 || h[j] < h[curr]) {
                        curr = j;
                    }
                }
                p[i] = curr;
                v[curr] = true;
            }
        }
        int dmin = 0;
        int dmax = 1000;
        int[] ret = null;
        while (dmin < dmax) {
            boolean found = true;
            int d = (dmax + dmin) / 2;
            int[] t = new int[n];
            boolean[] v = new boolean[n];
            t[0] = p[0];
            v[p[0]] = true;
            int added = p[0];
            int count = 1;
            while (true) {
                int good = -1;
                int pdiff = -1;
                for (int i = 0; i < n; ++i) {
                    if (v[i])
                        continue;
                    int diff = h[i] - h[added];
                    if (pdiff < diff && diff <= d) {
                        good = i;
                        pdiff = diff;
                    }
                }
                if (good < 0) {
                    break;
                }
                t[count++] = good;
                added = good;
                v[added] = true;
            }
            for (int i = n - 1; 0 < i; --i) {
                if (!v[p[i]]) {
                    t[count++] = p[i];
                }
            }
            for (int i = 0; i < n; ++i) {
                if (d < Math.abs(h[t[i]] - h[t[(i + 1) % n]])) {
                    found = false;
                    break;
                }
            }
            if (found) {
                assert(0 < count);
                dmax = d;
                ret = t.clone();
                // System.err.println(d);
            } else {
                dmin = d + 1;
            }
        }
        return ret;
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(5, (new FoxesOfTheRoundTable()).minimalDifference(
                    new int[] { 948, 510, 1000, 158, 639, 364, 274, 576, 186, 311, 886, 320, 812, 737 }),
                    new int[] { 3, 6, 11, 1, 4, 12, 0, 2, 10, 13, 7, 5, 9, 8 });
            eq(0, (new FoxesOfTheRoundTable()).minimalDifference(new int[] { 1, 99, 50, 50 }),
                    new int[] { 0, 3, 1, 2 });
            eq(1, (new FoxesOfTheRoundTable()).minimalDifference(new int[] { 123, 456, 789 }), new int[] { 0, 1, 2 });
            eq(2, (new FoxesOfTheRoundTable()).minimalDifference(new int[] { 10, 30, 40, 50, 60 }),
                    new int[] { 0, 1, 4, 3, 2 });
            eq(3, (new FoxesOfTheRoundTable()).minimalDifference(new int[] { 1, 2, 3, 4, 8, 12, 13, 14 }),
                    new int[] { 0, 1, 2, 3, 5, 6, 7, 4 });
            eq(4, (new FoxesOfTheRoundTable())
                    .minimalDifference(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }),
                    new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 });
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, int[] a, int[] b) {
        if (a.length != b.length) {
            System.err.println(
                    "Case " + n + " failed: returned " + a.length + " elements; expected " + b.length + " elements.");
            return;
        }
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. Expected and returned array differ in position " + i);
                print(b);
                print(a);
                return;
            }
        System.err.println("Case " + n + " passed.");
    }

    private static void print(int[] rs) {
        if (rs == null)
            return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(long[] rs) {
        if (rs == null)
            return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print(rs[i]);
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }

    private static void print(String[] rs) {
        if (rs == null)
            return;
        System.err.print('{');
        for (int i = 0; i < rs.length; i++) {
            System.err.print("\"" + rs[i] + "\"");
            if (i != rs.length - 1)
                System.err.print(", ");
        }
        System.err.println('}');
    }
}
