
public class ProblemSets {
    public long maxSets(long E, long EM, long M, long MH, long H) {
        long l = 0;
        long r = Long.MAX_VALUE;
        long ret = -1;
        while (l < r) {
            long d = l + (r - l) / 2;
            if (f(d, E, EM, M, MH, H)) {
                ret = d;
                l = d + 1;
            } else {
                r = d;
            }
        }
        return ret;
    }

    boolean f(long d, long E, long EM, long M, long MH, long H) {
        E -= d;
        M -= d;
        H -= d;
        if (E < 0) {
            long x = Math.min(-E, EM);
            E += x;
            EM -= x;
        }
        if (M < 0) {
            long x = Math.min(-M, EM);
            M += x;
            EM -= x;
        }
        if (M < 0) {
            long x = Math.min(-M, MH);
            M += x;
            MH -= x;
        }
        if (H < 0) {
            long x = Math.min(-H, MH);
            H += x;
            MH -= x;
        }
        return !(E < 0 || M < 0 || H < 0);
    }

    // BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(6, (new ProblemSets()).maxSets(563411256982316815L, 396646749275446584L, 10830259274061248L,
                    369314785529224450L, 706727134601021100L), 670101525530524548L);
            eq(5, (new ProblemSets()).maxSets(1, 1000, 200, 0, 300), 300L);
            eq(0, (new ProblemSets()).maxSets(2L, 2L, 1L, 2L, 2L), 3L);
            eq(1, (new ProblemSets()).maxSets(100L, 100L, 100L, 0L, 0L), 0L);
            eq(2, (new ProblemSets()).maxSets(657L, 657L, 657L, 657L, 657L), 1095L);
            eq(3, (new ProblemSets()).maxSets(1L, 2L, 3L, 4L, 5L), 3L);
            eq(4, (new ProblemSets()).maxSets(1000000000000000000L, 1000000000000000000L, 1000000000000000000L,
                    1000000000000000000L, 1000000000000000000L), 1666666666666666666L);
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, long a, long b) {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected \"" + b + "L, received " + a + "L.");
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
