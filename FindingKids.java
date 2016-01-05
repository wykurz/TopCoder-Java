import java.util.*;

public class FindingKids
{
    final int M = 1000000007;

    int n;
    int q;
    long[] pos;
    boolean[] dir; // false - left, true - right

    int[] kid;
    long[] time;

    void setup(long A, long B, long C)
    {
        Set<Long> pm = new HashSet<Long>();
        pos = new long[n];
        dir = new boolean[n];
        for (int i = 0; i < n; ++i) {
            A = (A * B + C) % M;
            long p = A % (M - n + i + 1);
            if (pm.contains(p))
                p = M - n + i;
            pos[i] = p;
            pm.add(p);
            if (p % 2 == 0)
                dir[i] = true;
            else
                dir[i] = false;
        }
        // Queries:
        kid = new int[q];
        time = new long[q];
        for (int i = 0; i < q; ++i) {
            A = (A * B + C) % M;
            kid[i] = (int) (A % n);
            A = (A * B + C) % M;
            time[i] = A;
        }
    }

    int[] idx;
    int[] ridx;

    void indices()
    {
        idx = new int[n];
        ridx = new int[n];
        long[] sPos = pos.clone();
        Arrays.sort(sPos);
        boolean[] done = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int j = Arrays.binarySearch(sPos, pos[i]);
            while (done[j])
                ++j;
            assert(pos[i] == sPos[j]);
            idx[i] = j;
            ridx[j] = i;
            done[j] = true;
        }
    }

    int[] left;
    int[] right;

    void split()
    {
        int cr = 0;
        for (int i = 0; i < n; ++i) {
            if (dir[i])
                ++cr;
        }
        left = new int[n - cr];
        right = new int[cr];
        cr = 0;
        int cl = 0;
        for (int i = 0; i < n; ++i) {
            if (dir[ridx[i]])
                right[cr++] = ridx[i];
            else
                left[cl++] = ridx[i];
        }
    }

    long lpos(int ord, long t)
    {
        if (ord == 0)
            return Long.MIN_VALUE;
        if (left.length < ord)
            return Long.MAX_VALUE;
        return pos[left[ord - 1]] - t;
    }

    long rpos(int ord, long t)
    {
        if (ord == 0)
            return Long.MIN_VALUE;
        if (right.length < ord)
            return Long.MAX_VALUE;
        return pos[right[ord - 1]] + t;
    }

    long query(int i)
    {
        int k = kid[i];
        long t = time[i];
        final int ord = idx[k] + 1;
        int lord = ord;
        int step = ord / 2 + 1;
        for (;;) {
            boolean lhigh = false;
            int rord = ord - lord;
            if (lord <= left.length && rord <= right.length) {
                long pl = lpos(lord, t);
                long pln = lpos(lord + 1, t);
                long pr = rpos(rord, t);
                long prn = rpos(rord + 1, t);
                if (pl < prn && pr < pln) {
                    return Math.abs(Math.max(pl, pr));
                }
                if (prn < pl) {
                    lhigh = true;
                }
            } else if (left.length < lord) {
                lhigh = true;
            }
            assert(0 < step);
            if (lhigh)
                lord = Math.max(0, lord - step);
            else
                lord = Math.min(ord, lord + step);
            step = step / 2 + (step & 1);
        }
    }

    public long getSum(int N, int Q, int A, int B, int C)
    {
        n = N;
        q = Q;
        setup(A, B, C);
        indices();
        split();
        long total = 0;
        for (int i = 0; i < q; ++i) {
            total += query(i);
        }
        return total;
    }

    // BEGIN CUT HERE
    public static void main(String[] args)
    {
        try {
            // eq(3, (new FindingKids()).getSum(10, 5, 0, 1, 1), 15L);
            eq(0, (new FindingKids()).getSum(5, 2, 0, 1, 1), 15L);
            eq(1, (new FindingKids()).getSum(5, 4, 3, 2, 1), 43376L);
            eq(2, (new FindingKids()).getSum(200000, 200000, 12345, 67890, 111213141), 133378408428237L);
            eq(3, (new FindingKids()).getSum(1, 200000, 299935478, 93657707, 751975948), 55916462670542L);
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, long a, long b)
    {
        if (a == b)
            System.err.println("Case " + n + " passed.");
        else
            System.err.println("Case " + n + " failed: expected \"" + b + "L, received " + a + "L.");
    }
    // END CUT HERE
}
