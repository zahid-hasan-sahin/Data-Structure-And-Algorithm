
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class usingInterval {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        char[] a = scan.next().toCharArray();
        int n = a.length;
        ArrayList<interval> inter = new ArrayList<interval>();

        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            if (a[i] == '(') {
                st.add(i);
            } else if (!st.isEmpty()) {

                inter.add(new interval(st.pop(), i));

            }
        }
        Collections.sort(inter, new myCom());
        if (inter.size() == 0) {
            System.out.println("0 1");
            return;
        }
       
        int l = inter.get(0).l;
        int r = inter.get(0).r;
        boolean[] isCut = new boolean[inter.size()];

        for (int i = 1; i < inter.size(); i++) {

            if (inter.get(i).l > l && inter.get(i).r < r) {
                isCut[i] = true;
            } else {
                l = inter.get(i).l;
                r = inter.get(i).r;
            }
        }
        ArrayList<interval> valid = new ArrayList<interval>();
        for (int i = 0; i < inter.size(); i++) {
            if (!isCut[i]) {
                valid.add(inter.get(i));
            }
        }

        ArrayList<interval> finalValid = new ArrayList<interval>();
        finalValid.add(new interval(valid.get(0).l, valid.get(0).r));
        int q = 0;
        for (int i = 1; i < valid.size(); i++) {
            if (finalValid.get(q).r + 1 == valid.get(i).l) {
                finalValid.add(new interval(finalValid.get(q).l, valid.get(i).r));
                ++q;
            } else {
                finalValid.add(new interval(valid.get(i).l, valid.get(i).r));
                ++q;
            }
        }

        int mx = 0;
        for (int i = 0; i < finalValid.size(); i++) {
            mx = Math.max(mx, (finalValid.get(i).r - finalValid.get(i).l + 1));
        }

        int res = 0;
        for (int i = 0; i < finalValid.size(); i++) {
            if (finalValid.get(i).r - finalValid.get(i).l + 1 == mx) {
                ++res;
            }
        }
        System.out.println(mx + " " + res);

    }

}

class interval {

    int l, r;

    interval(int a, int b) {
        l = a;
        r = b;
    }
}

class myCom implements Comparator<interval> {

    public int compare(interval a, interval b) {
        return a.l - b.l;
    }

}
