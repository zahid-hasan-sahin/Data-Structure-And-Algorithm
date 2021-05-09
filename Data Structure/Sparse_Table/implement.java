/*

Given a sequence of integers a1, ..., an and q queries x1, ..., xq on it. 
For each query xi you have to count the number of pairs (l, r) such that 1 ≤ l ≤ r ≤ n and gcd(al, al + 1, ..., ar) = xi.

Basically its solution for: https://codeforces.com/contest/475/problem/D

*/




import java.util.HashMap;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.FileInputStream;

import java.io.IOException;

public class SparseTableImplement {

    public static void main(String args[]) throws IOException {
        Reader scan = new Reader();
        int n = scan.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        SparseTable sp = new SparseTable(a);
        HashMap<Integer, Long> h = new HashMap<Integer, Long>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                int l = j;
                int r = n - 1;
                int next = j;
                int val = sp.query(i, j);

                while (l <= r) {

                    int mid = (l + r) >> 1;
                    if (sp.query(i, mid) == val) {
                        l = mid + 1;
                        next = mid;
                    } else {
                        r = mid - 1;
                    }
                }

                h.put(val, h.getOrDefault(val, (long) 0) + (next - j + 1));
                j = next;

            }
        }

        StringBuilder sb = new StringBuilder();

        int q = scan.nextInt();
        while (q-- > 0) {
            int in = scan.nextInt();
            sb.append(h.getOrDefault(in, (long) 0) + "\n");
        }
        System.out.println(sb);

    }

}

class SparseTable {

    int n, log;
    int[][] table;

    SparseTable(int[] a) {

        n = a.length;
        log = 32 - Integer.numberOfLeadingZeros(n - 1);
        table = new int[n + 1][log + 1];

        for (int i = 0; i < n; i++) {
            table[i][0] = a[i];
        }

        for (int i = 1; i <= log; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                table[j][i] = gcd(table[j][i - 1], table[j + (1 << (i - 1))][i - 1]);
            }
        }

    }

    int query(int l, int r) {
        if (l > r) {
            return 0;
        }
        int lg = 31 - Integer.numberOfLeadingZeros(r - l + 1);

        return gcd(table[l][lg], table[r - (1 << lg) + 1][lg]);

    }

    int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

class Reader {

    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length 
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                break;
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }
}
