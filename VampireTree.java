import java.util.*;

public class VampireTree
{
    public int maxDistance(int[] num)
    {
        int n = num.length;
        int sum = 0;
        for (int x : num)
            sum += x;
        if (2 * (n - 1) != sum)
            return -1;
        int nleaves = 0;
        for (int x : num)
            if (x == 1)
                nleaves++;
        return 2 + n - nleaves - 1;
    }

}
