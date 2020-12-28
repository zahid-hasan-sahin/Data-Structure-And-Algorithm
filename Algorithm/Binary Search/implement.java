
import java.util.Scanner;

class binarSearch {

    static int binarySearch(long arr[], int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x) {
                return m;
            }
            if (arr[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    static int lower(long[] ages, int target) {
        if (ages.length == 0) {
            return 0;
        }
        int l = 0;
        int r = ages.length - 1;
        if (target <= ages[0]) {
            return 0;
        }
        if (target > ages[r]) {
            return -1;
        }
        while (l < r) {
            int m = l + (r - l) / 2;

            if (ages[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r;
    }

    static int upper(long[] ages, int target) {
        if (ages.length == 0) {
            return 0;
        }
        int l = 0;
        int r = ages.length - 1;
        if (target < ages[0]) {
            return -1;
        }
        if (target >= ages[r]) {
            return r;
        }
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (ages[m] <= target) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return ages[r] <= target ? r : l;
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

    }
}
