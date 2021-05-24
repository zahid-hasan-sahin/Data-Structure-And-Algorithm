
import java.util.Scanner;

public class Tries {

    static TrieNode root;

    static void insert(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'a';
            if (curr.child[ind] == null) {
                curr.child[ind] = new TrieNode();
            }
            curr = curr.child[ind];
        }
        curr.isLeaf = true;
    }

    static boolean search(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'a';
            if (curr.child[ind] == null) {
                return false;
            }
            curr = curr.child[ind];
        }
        if (curr.isLeaf) {
            return true;
        }
        return false;
    }

    static boolean isEmpty(TrieNode r) {
        for (int i = 0; i < 26; i++) {
            if (r.child[i] != null) {
                return false;
            }
        }
        return true;
    }

    static TrieNode delete(TrieNode root, String s, int d) {
        if (root == null) {
            return null;
        }
        if (s.length() == d) {
            root.isLeaf = false;
            if (isEmpty(root)) {
                root = null;
            }

            return root;
        }
        int inx = s.charAt(d) - 'a';
        root.child[inx] = delete(root.child[inx], s, d + 1);
        if (isEmpty(root) && !root.isLeaf) {
            root = null;
        }
        return root;
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        root = new TrieNode();
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            insert(scan.next());
        }

    }

}

class TrieNode {

    int sz = 26;
    boolean isLeaf;
    TrieNode[] child = new TrieNode[sz];

    TrieNode() {
        for (int i = 0; i < sz; i++) {
            child[i] = null;

        }
        isLeaf = false;
    }
}
