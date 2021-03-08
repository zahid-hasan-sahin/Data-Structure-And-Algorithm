
class MultiSet<E> {

    int size = 0;
    private TreeSet<E> set = new TreeSet<E>();
    private HashMap<E, Integer> occour = new HashMap<E, Integer>();

    void add(E v) {
        ++size;
        if (occour.containsKey(v)) {
            occour.put(v, occour.get(v) + 1);
            if (occour.get(v) == 1) {
                set.add(v);
            }
        } else {
            set.add(v);
            occour.put(v, 1);
        }
    }

    void getFullSet() {
        // O(n)
        ArrayList<E> ar = new ArrayList<E>();
        for (E i : set) {
            int p = occour.get(i);
            for (int j = 0; j < p; j++) {
                ar.add(i);
            }
        }
        for (int i = 0; i < ar.size(); i++) {
            System.out.print(ar.get(i) + " ");
        }
        System.out.println();

    }

    boolean isEmpty() {
        return set.isEmpty();
    }

    E getFirst() {
        for (E i : set) {

            return i;
        }
        return null;
    }

    void remove(E el) {
        if (occour.containsKey(el) && occour.get(el) >= 1) {
            --size;
            occour.put(el, occour.get(el) - 1);
            if (occour.get(el) == 0) {
                set.remove(el);
            }
        }
    }

    int setCount(E k) {
        if (!occour.containsKey(k)) {
            return 0;
        }
        return occour.get(k);
    }

    int size() {
        return size;
    }

}
