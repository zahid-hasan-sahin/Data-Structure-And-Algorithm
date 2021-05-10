

import java.util.*;

public class usingDp {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        char[] a = scan.next().toCharArray();
        int n = a.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }
        Stack<Integer> st = new Stack<Integer>();
        int mx = 0;
        int res = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] == '(') {
                st.add(i);
            } else {
                if (st.isEmpty()) {
                    continue;
                }
                int pre = st.pop();
                dp[i] = pre;
                if (pre != 0 && dp[pre - 1] != -1) {
                    dp[i] = dp[pre - 1];
                }
                if (i - dp[i] + 1 > mx) {
                    mx = i - dp[i] + 1;
                    res = 0;
                }
                if (i - dp[i] + 1 == mx) {
                    ++res;
                }
            }
        }
        System.out.println(mx + " " + res);
    }
}
