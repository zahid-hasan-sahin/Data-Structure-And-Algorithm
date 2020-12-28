
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class margeSortTree {

    /*
        1Base Index;
        return the number of elements greater than k in the subsequence a[l] to a[r];
        Build a merge sort tree takes O(NlogN);
        complexity of O(LogN * LogN) per query;
    
     */
    static void buildMst(int[] a, ArrayList<Integer>[] tree, int mIndex, int mStart, int mEnd) {
        if (mStart == mEnd) {
            tree[mIndex] = new ArrayList<Integer>();
            tree[mIndex].add(a[mEnd]);
            return;
        }
        int mid = (mStart + mEnd) / 2;
        buildMst(a, tree, mIndex * 2, mStart, mid);
        buildMst(a, tree, mIndex * 2 + 1, mid + 1, mEnd);

        int i = 0;
        int j = 0;
        tree[mIndex] = new ArrayList<Integer>();
        while (i < tree[mIndex * 2].size() && j < tree[mIndex * 2 + 1].size()) {
            if (tree[mIndex * 2].get(i) < tree[mIndex * 2 + 1].get(j)) {

                tree[mIndex].add(tree[mIndex * 2].get(i));
                ++i;
            } else {

                tree[mIndex].add(tree[mIndex * 2 + 1].get(j));
                ++j;
            }
        }
        while (i < tree[mIndex * 2].size()) {

            tree[mIndex].add(tree[mIndex * 2].get(i));
            ++i;
        }
        while (j < tree[mIndex * 2 + 1].size()) {

            tree[mIndex].add(tree[mIndex * 2 + 1].get(j));
            ++j;
        }

    }

    static int query(ArrayList<Integer>[] tree, int mIndex, int mStart, int mEnd, int qStart, int qEnd, int k) {
        if (qStart > mEnd || qEnd < mStart) {
            return 0;
        }
        if (qStart <= mStart && qEnd >= mEnd) {

            return tree[mIndex].size() - upper(tree[mIndex], k) - 1;
        }
        int mid = (mStart + mEnd) / 2;
        int l = query(tree, mIndex * 2, mStart, mid, qStart, qEnd, k);
        int r = query(tree, mIndex * 2 + 1, mid + 1, mEnd, qStart, qEnd, k);
        return l + r;
    }

    static int upper(ArrayList<Integer> ages, int target) {
        if (ages.size() == 0) {
            return 0;
        }
        int l = 0;
        int r = ages.size() - 1;
        if (target < ages.get(0)) {
            return -1;
        }
        if (target >= ages.get(r)) {
            return r;
        }
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (ages.get(m) <= target) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return ages.get(r) <= target ? r : l;
    }

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = scan.nextInt();
        int[] a = new int[n + 1];
        ArrayList<Integer>[] tree = new ArrayList[4 * n];

        for (int i = 1; i < n + 1; i++) {
            a[i] = scan.nextInt();
        }
        buildMst(a, tree, 1, 1, n);
        int q = scan.nextInt();
        while (q-- > 0) {
            int l = scan.nextInt();
            int r = scan.nextInt();
            int k = scan.nextInt();
            System.out.println((query(tree, 1, 1, n, l, r, k)));
        }

    }
}
