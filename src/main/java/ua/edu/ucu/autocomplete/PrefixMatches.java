package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.List;


public class PrefixMatches {
    private int MIN_LEN = 3;
    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        if (strings == null) {
            return 0;
        }
        int wordsCount = 0;

        for (String str : strings) {
            String[] words = str.split("\\s+");
            for (String word : words) {
                if (word.length() > 2) {
                    this.trie.add(new Tuple(word, word.length()));
                    wordsCount++;
                }
            }
        }
        return wordsCount;
    }

    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException("Length of prefix less than 2.");
        }
        return this.trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException("Length of prefix less than 2.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("Length of words less than 0.");
        }
        Iterable<String> wordsWP = this.wordsWithPrefix(pref);
        List<String> kWords = new ArrayList<>();

        int length = Math.max(pref.length(), MIN_LEN);
        for (int i = length; i < k + length; i++) {
            for (String word : wordsWP){
                if (word.length() == i) kWords.add(word);
            }
        }
        return kWords;
    }

    public int size() {
        return this.trie.size();
    }
}
