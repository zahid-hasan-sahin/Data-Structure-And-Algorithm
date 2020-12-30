
import java.util.Scanner;

public class implement {

    /*
        EveryThing must be 1 Base
        length of Tree array and lazy array must be length of a * 4
        This is implemention of Min value of L to R
        
     */
    static void buildSeg(long[] a, long[] tree, int sIndex, int sStart, int sEnd) {
        
        if (sStart == sEnd) {
            tree[sIndex] = a[sStart];
            return;
        }
        int m = (sStart + sEnd) / 2;
        buildSeg(a, tree, sIndex * 2, sStart, m);
        buildSeg(a, tree, sIndex * 2 + 1, m + 1, sEnd);
        tree[sIndex] = Math.min(tree[sIndex * 2], tree[sIndex * 2 + 1]);
    }

    static long query(long[] tree, int sIndex, int sStart, int sEnd, int qStart, int qEnd) {
        if (sEnd < qStart) {
            return Integer.MAX_VALUE;
        } else if (sStart > qEnd) {
            return Integer.MAX_VALUE;
        }
        if (sStart >= qStart && sEnd <= qEnd) {
            return tree[sIndex];
        }
        int mid = (sStart + sEnd) / 2;
        long x = query(tree, 2 * sIndex, sStart, mid, qStart, qEnd);
        long y = query(tree, 2 * sIndex + 1, mid + 1, sEnd, qStart, qEnd);
        return Math.min(x, y);
    }

    static void updatePoint(long[] a, long[] tree, int sIndex, int sStart, int sEnd, int q, int val) {
        if (sStart == sEnd) {
            tree[sIndex] = val;
            a[q] = val;
            return;
        }
        int m = (sStart + sEnd) / 2;
        if (q <= m) {
            updatePoint(a, tree, sIndex * 2, sStart, m, q, val);
        } else {
            updatePoint(a, tree, sIndex * 2 + 1, m + 1, sEnd, q, val);
        }
        tree[sIndex] = Math.min(tree[sIndex * 2], tree[sIndex * 2 + 1]);
    }

    static void updateRange(long[] tree, long lazy[], int sIndex, int sStart, int sEnd, int rStart, int rEnd, long val) {

        if (lazy[sIndex] != 0) {
            tree[sIndex] += lazy[sIndex] * (sEnd - sStart + 1);
            if (sStart != sEnd) {
                lazy[sIndex * 2] += lazy[sIndex];
                lazy[sIndex * 2 + 1] += lazy[sIndex];
            }
            lazy[sIndex] = 0;
        }

        if (sStart > rEnd || sEnd < rStart) {
            return;
        }
        if (sStart >= rStart && sEnd <= rEnd) {
            tree[sIndex] += ((sEnd - sStart + 1) * val);
            if (sStart != sEnd) {
                lazy[sIndex * 2] += val;
                lazy[sIndex * 2 + 1] += val;
            }
            return;
        }
        int m = (sStart + sEnd) / 2;
        updateRange(tree, lazy, sIndex * 2, sStart, m, rStart, rEnd, val);
        updateRange(tree, lazy, sIndex * 2 + 1, m + 1, sEnd, rStart, rEnd, val);
        tree[sIndex] = Math.min(tree[sIndex * 2], tree[sIndex * 2 + 1]);
    }

    static long rangeQuery(long[] tree, long[] lazy, int sIndex, int sStart, int sEnd, int qStart, int qEnd) {
        if (lazy[sIndex] != 0) {
            tree[sIndex] += lazy[sIndex] * (sEnd - sStart + 1);
            if (sStart != sEnd) {
                lazy[sIndex * 2] += lazy[sIndex];
                lazy[sIndex * 2 + 1] += lazy[sIndex];
            }
            lazy[sIndex] = 0;
        }

        if (sStart > qEnd || sEnd < qStart) {
            return Integer.MAX_VALUE;
        }
        if (sStart >= qStart && sEnd <= qEnd) {
            return tree[sIndex];
        }
        int m = (sStart + sEnd) / 2;
        long left = rangeQuery(tree, lazy, sIndex * 2, sStart, m, qStart, qEnd);
        long right = rangeQuery(tree, lazy, sIndex * 2 + 1, m + 1, sEnd, qStart, qEnd);
        return Math.min(left, right);
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        long[] a = new long[len + 1];
        long[] tree = new long[len * 4];
        long[] lazy = new long[len * 4];
        for (int i = 1; i <= len; i++) {
            a[i] = scan.nextInt();
        }
        buildSeg(a, tree, 1, 1, len);

        long q = scan.nextInt();

        while (q-- > 0) {
            char c = scan.next().charAt(0);
            if (c == '0') {
                int l = scan.nextInt();
                int r = scan.nextInt();
                int v = scan.nextInt();
                updateRange(tree, lazy, 1, 1, len, l, r, v);
            } else {
                int l = scan.nextInt();
                int r = scan.nextInt();
                System.out.println(rangeQuery(tree, lazy, 1, 1, len, l, r));
            }
        }
    }

}
