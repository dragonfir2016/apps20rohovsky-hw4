package ua.edu.ucu.tries;

/*
 * This class was implemented using code from Algorithms 4th Edition Robert Sedgewick book
 * and slightly altering it to satisfy the requirements of the task.
*/

import ua.edu.ucu.collections.Queue;

import java.util.ArrayList;

public class RWayTrie implements Trie {
    private static final int R = 256;
    private Node root = new Node();


    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        this.root = put(this.root, t.term, t.weight, 0);
    }

    private Node put(Node x, String key, int val, int CurrDepth) {
        if (x == null) x = new Node();

        if (CurrDepth == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(CurrDepth);
        x.next[c] = put(x.next[c], key, val, CurrDepth + 1);
        return x;
    }


    @Override
    public boolean contains(String word) {                 ////////////////////////////////////////////////////
        return (int) get(word) != -1;
    }

    public Object get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;

        return x.val;
    }

    private Node get(Node x, String key, int currDepth) { // Return node associated with key in
        if (x == null) return null;                       // the subtrie rooted at x.
        if (currDepth == key.length()) return x;

        char c = key.charAt(currDepth); // Use dth key char to identify subtrie.
        return get(x.next[c], key, currDepth + 1);
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        return root != null;
    }

    private Node delete(Node x, String key, int currDepth) {
        if (x == null) return null;

        if (currDepth == key.length())
            x.val = null;
        else {
            char c = key.charAt(currDepth);
            x.next[c] = delete(x.next[c], key, currDepth + 1);
        }
        if (x.val != null) return x;

        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue queue = new Queue();
        collect(get(this.root, s, 0), s, queue);

        ArrayList<String> wordsLst = new ArrayList<>();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            wordsLst.add((String) queue.dequeue());
        }
        return wordsLst;
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) return;

        if (x.val != null) q.enqueue(pre);

        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;

        int currSize = 0;
        if (x.val != null) currSize++;

        for (char c = 0; c < R; c++)
            currSize += size(x.next[c]);
        return currSize;
    }
}